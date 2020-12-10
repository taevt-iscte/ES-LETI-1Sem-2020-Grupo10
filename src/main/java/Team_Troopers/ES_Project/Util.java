package Team_Troopers.ES_Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Classe de funções estáticas de utilidades usadas no resto do programa
 * 
 * @author Tiago Torres
 *
 */
public class Util {

	/**
	 * Método auxiliar desenvolvido com o propósito de adquirir os valores presentes na sheet da classe, a fim de os devolver a qualquer um dos controladores que assim
	 * precisar.
	 * 
	 * @author   Tiago Torres
	 */
	public static ArrayList<ExcelRecord> parseExcel(File file) throws EncryptedDocumentException, IOException {
		Sheet sheet = WorkbookFactory.create(file).getSheetAt(0);
		ArrayList<ExcelRecord> recordList = new ArrayList<ExcelRecord>();
		for (int row = 1; row < sheet.getLastRowNum(); row++) {
			Row r = sheet.getRow(row);
			int id = (int) r.getCell(0).getNumericCellValue();
			String package_ = r.getCell(1).getStringCellValue();
			String class_ = r.getCell(2).getStringCellValue();
			String method = r.getCell(3).getStringCellValue();
			int loc = (int) r.getCell(4).getNumericCellValue();
			int cyclo = (int) r.getCell(5).getNumericCellValue();
			int atfd = (int) r.getCell(6).getNumericCellValue();
			double laa = 0;
			switch (r.getCell(7).getCellType()) {
			case NUMERIC:
				laa = r.getCell(7).getNumericCellValue();
				break;
			case STRING:
				laa = Double.parseDouble(r.getCell(7).getStringCellValue());
				break;
			default:
				break;
			}
			boolean is_long_method = r.getCell(8).getBooleanCellValue();
			boolean iPlasma = r.getCell(9).getBooleanCellValue();
			boolean pmd = r.getCell(10).getBooleanCellValue();
			boolean is_feature_envy = r.getCell(11).getBooleanCellValue();
			recordList.add(new ExcelRecord(id, package_, class_, method, loc, cyclo, atfd, laa, is_long_method, iPlasma,
					pmd, is_feature_envy));
		}
		return recordList;
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de retirar os valores calculados a partir das regras do utilizador, devolvendo os valores default.
	 * 
	 * @author   Tiago Torres
	 */
	public static HashMap<EvalType, Integer> countTypes(ArrayList<ExcelRecord> list) {
		HashMap<EvalType, Integer> counting = new HashMap<EvalType, Integer>();
		for (EvalType e : EvalType.values())
			counting.put(e, 0);
		counting.remove(EvalType.USER_DCI); counting.remove(EvalType.USER_DII);
		counting.remove(EvalType.USER_ADCI); counting.remove(EvalType.USER_ADII);
		list.forEach(record -> {
			counting.put(record.getEval()[0], counting.get(record.getEval()[0])+1);
			counting.put(record.getEval()[1], counting.get(record.getEval()[1])+1);
		});
		return counting;
	}

	/**
	 * Método auxiliar responsável por atualizar os valores a serem utilizados pelos outros controladores relativamente à avaliação dos valores presentes no ficheiro.
	 * 
	 * @author   João Polónio
	 */

	public static HashMap<EvalType, Integer> count_user(HashMap<EvalType, Integer> counting, ArrayList<ExcelRecord> recordList, ArrayList<String> userArray,
			boolean useUser) {
		EvalType[] list = {EvalType.USER_DCI, EvalType.USER_DII, EvalType.USER_ADCI, EvalType.USER_ADII};
		boolean found = false;
		for (EvalType e : list) {
			found = found || counting.containsKey(e);
		}

		if (found) {
			counting.remove(EvalType.USER_DCI);
			counting.remove(EvalType.USER_DII);
			counting.remove(EvalType.USER_ADCI);
			counting.remove(EvalType.USER_ADII);
		}
		ArrayList<String> rules = new ArrayList<>();
		ArrayList<String> op = new ArrayList<>();
		for (int i = 0; i < userArray.size(); i++) {
			if (i % 2 == 0)
				rules.add(userArray.get(i));
			else
				op.add(userArray.get(i)); 
		}
		if (!rules.isEmpty())
			for (ExcelRecord r : recordList) {
				ArrayList<Boolean> results = new ArrayList<>();
				for (String rule : rules) {
					results.add(applyRule(rule.split(" "), r));
				}
				boolean pass = calcPass(results, op);
				counting.put(r.userEval(pass), counting.getOrDefault(r.userEval(pass), 0) + 1);
			}

		if (!useUser) {
			counting.remove(EvalType.USER_DCI);
			counting.remove(EvalType.USER_DII);
			counting.remove(EvalType.USER_ADCI);
			counting.remove(EvalType.USER_ADII);
		}
		return counting;
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de regular a composição de regras definidas pelo utilizador.
	 * 
	 * @param    parts			   Array que agrupa o conjunto de métricas refletoras do método avaliado.
	 * @param	  r			   	   Linha de excel a ser desdobrada em blocos de informação útil à avaliação.
	 * @return   pass    		   Valor booleano responsável pela avaliação de cada uma das métricas.
	 * @author   Tiago Torres
	 */

	private static boolean applyRule(String[] parts, ExcelRecord r) {
		double val = 0;
		switch(parts[0]) {
		case "LOC":
			val = (double) r.getLoc();
			break;
		case "CYCLO":
			val = (double) r.getCyclo();
			break;
		case "ATFD":
			val = (double) r.getAtfd();
			break;
		case "LAA":
			val = (double) r.getLaa();
			break;
		}
		double thresh = Double.parseDouble(parts[2]);
		boolean pass = false;
		switch(parts[1]) {
		case "<":
			pass = val < thresh;
			break;
		case ">":
			pass = val > thresh;
			break;
		}
		return pass;
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de regular a composição de regras definidas pelo utilizador.
	 * 
	 * @param    bools			   Array que agrupa o conjunto de booleanos definidos pelo utilizador, por ordem.
	 * @param	  op			   Array que agrupa o conjunto de operações definidas pelo utilizador, por ordem.
	 * @return   bools.get(0)     Valor booleano definitivo em relação ao conjunto de booleanos definidos.
	 * @author   Tiago Torres
	 */

	private static boolean calcPass(ArrayList<Boolean> bools, ArrayList<String> op) {
		boolean end = false;
		int index1, index2, index3;
		while (bools.size() != 1) {
			if (!end) {
				index1 = 0;
				index2 = 1;
				index3 = 0;
			} else {
				index1 = bools.size()-1;
				index2 = bools.size()-2;
				index3 = op.size()-1;
			}
			switch (op.get(index3)) {
			case "AND":
				bools.set(index2, bools.get(index1) && bools.get(index2));
				break;
			case "OR":
				bools.set(index2, bools.get(index1) || bools.get(index2));
				break;
			}
			bools.remove(index1);
			op.remove(index3);
			end = !end;
		}
		return bools.get(0);
	}

}
