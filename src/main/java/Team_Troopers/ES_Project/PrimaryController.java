package Team_Troopers.ES_Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Backend.EvalType;
import Backend.ExcelRecord;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador principal, responsável pela gestão backend da GUI, gerindo a
 * utilização de dados, bem como da sua representação ao utilizador.
 * 
 * @see App
 * @see ChartController
 * @see TableController
 * @see TextualController
 * @author Tiago Torres, João Polónio, José Raposo
 */

public class PrimaryController implements Initializable {

	@FXML
	private Button importExcel;
	@FXML
	private ComboBox<String> avaliarTools;
	@FXML
	private Button submitButton;
	@FXML
	private Button avaliarDadosButton;
	@FXML
	private CheckBox use_rules;
	@FXML
	private ComboBox<String> evalMethod;
	private static Sheet sheet;

	private Stage excelWindow;
	private boolean set = false;
	private Stage textualWindow;
	private Stage userWindow;
	private ArrayList<ExcelRecord> recordList;
	private HashMap<EvalType, Integer> counting;
	private ArrayList<String> userArray = new ArrayList<>();

	/**
	 * Permite inicializar o controlador assim que o objeto root terminar de
	 * processar.
	 * 
	 * @param location  localização do root object a usar inicializado.
	 * @param resources localização dos recursos a serem utilizados para localizar o
	 *                  root object.
	 * @author João Polónio
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
		recordList = new ArrayList<ExcelRecord>();
		counting = new HashMap<EvalType, Integer>();
		use_rules.selectedProperty().addListener(this::edit_count);
		evalMethod.getItems().addAll("Long Method", "Feature Envy");
		evalMethod.getSelectionModel().selectedItemProperty().addListener(this::goCount);
	}

	/**
	 * Permite lidar com o evento de fechar a janela principal do GUI, bem como a
	 * consequência de esta ser fechada em outras janelas possivelmente ainda
	 * ativas.
	 * 
	 * @param e Evento causado pelo encerramento da janela principal.
	 * @author João Polónio
	 */

	private void goCount(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		counting = count_user(counting, recordList, userArray, use_rules.isSelected());
	}

	public void closeMainWindow(WindowEvent e) {
		if (sheet == null)
			return;
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirmation");
		confirm.setHeaderText("You have an excel view open, exit?");
		Optional<ButtonType> response = confirm.showAndWait();
		if (response.get() == ButtonType.OK)
			excelWindow.close();
		else
			e.consume();
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de adquirir os valores presentes
	 * na sheet da classe, a fim de os devolver a qualquer um dos controladores que
	 * assim precisar.
	 * 
	 * @return
	 * @author Tiago Torres
	 */
	public ArrayList<ExcelRecord> parseExcel(File file) throws EncryptedDocumentException, IOException {
		sheet = WorkbookFactory.create(file).getSheetAt(0);
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
	 * Método auxiliar desenvolvido com o propósito de retirar os valores calculados
	 * a partir das regras do utilizador, devolvendo os valores default.
	 * 
	 * @author Tiago Torres
	 */
	public HashMap<EvalType, Integer> countTypes(ArrayList<ExcelRecord> list) {
		HashMap<EvalType, Integer> counting = new HashMap<EvalType, Integer>();
		for (EvalType e : EvalType.values())
			counting.put(e, 0);
		counting.remove(EvalType.USER_DCI);
		counting.remove(EvalType.USER_DII);
		counting.remove(EvalType.USER_ADCI);
		counting.remove(EvalType.USER_ADII);
		list.forEach(record -> {
			counting.put(record.getEval()[0], counting.get(record.getEval()[0]) + 1);
			counting.put(record.getEval()[1], counting.get(record.getEval()[1]) + 1);
		});
		return counting;
	}

	/**
	 * Método auxiliar responsável por atualizar os valores a serem utilizados pelos
	 * outros controladores relativamente à avaliação dos valores presentes no
	 * ficheiro.
	 * 
	 * @author João Polónio
	 */

	public HashMap<EvalType, Integer> count_user(HashMap<EvalType, Integer> counting, ArrayList<ExcelRecord> recordList,
			ArrayList<String> userArray, boolean useUser) {
		EvalType[] list = { EvalType.USER_DCI, EvalType.USER_DII, EvalType.USER_ADCI, EvalType.USER_ADII };
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
		if (evalMethod.getSelectionModel().getSelectedIndex() != -1
				&& evalMethod.getSelectionModel().getSelectedItem().equalsIgnoreCase("Feature Envy")) {
			counting.remove(EvalType.PLASMA_ADCI);
			counting.remove(EvalType.PLASMA_ADII);
			counting.remove(EvalType.PLASMA_DCI);
			counting.remove(EvalType.PLASMA_DII);
			counting.remove(EvalType.PMD_ADCI);
			counting.remove(EvalType.PMD_ADII);
			counting.remove(EvalType.PMD_DCI);
			counting.remove(EvalType.PMD_DII);
		} else {
			counting = countTypes(recordList);
		}
		ArrayList<String> rules = new ArrayList<>();
		ArrayList<String> op = new ArrayList<>();
		System.out.println(userArray);
		for (int i = 0; i < userArray.size(); i++) {
			if (i % 2 == 0)
				rules.add(userArray.get(i));
			else
				op.add(userArray.get(i));
		}
		if (!rules.isEmpty())
			for (ExcelRecord r : recordList) {
				@SuppressWarnings("unchecked")
				ArrayList<String> tempOp = (ArrayList<String>) op.clone();
				ArrayList<Boolean> results = new ArrayList<>();
				for (String rule : rules) {
					results.add(applyRule(rule.split(" "), r));
				}
				boolean pass = calcPass(results, tempOp);
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
	 * Método auxiliar desenvolvido com o propósito de regular a composição de
	 * regras definidas pelo utilizador.
	 * 
	 * @param parts Array que agrupa o conjunto de métricas refletoras do método
	 *              avaliado.
	 * @param r     Linha de excel a ser desdobrada em blocos de informação útil à
	 *              avaliação.
	 * @return pass Valor booleano responsável pela avaliação de cada uma das
	 *         métricas.
	 * @author Tiago Torres
	 */

	private static boolean applyRule(String[] parts, ExcelRecord r) {
		double val = 0;
		switch (parts[0]) {
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
		switch (parts[1]) {
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
	 * Método auxiliar desenvolvido com o propósito de regular a composição de
	 * regras definidas pelo utilizador.
	 * 
	 * @param bools Array que agrupa o conjunto de booleanos definidos pelo
	 *              utilizador, por ordem.
	 * @param op    Array que agrupa o conjunto de operações definidas pelo
	 *              utilizador, por ordem.
	 * @return bools.get(0) Valor booleano definitivo em relação ao conjunto de
	 *         booleanos definidos.
	 * @see App
	 * @author Tiago Torres
	 */

	private static boolean calcPass(ArrayList<Boolean> bools, ArrayList<String> op) {
		boolean end = false;
		int index1, index2, index3;
		System.out.println(bools);
		System.out.println(op);
		while (bools.size() > 1) {
			if (!end) {
				index1 = 0;
				index2 = 1;
				index3 = 0;
			} else {
				index1 = bools.size() - 1;
				index2 = bools.size() - 2;
				index3 = op.size() - 1;
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

	/**
	 * Método auxiliar da classe, responsável por lidar com os valores de avaliação
	 * de acordo com a possibilidade de existência de regras ativas do utilizador.
	 * 
	 * @param observable Evento causado pelo encerramento da janela principal.
	 * @param oldValue
	 * @param newValue
	 * @author Tiago Torres
	 */

	private void edit_count(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		counting = count_user(counting, recordList, userArray, newValue);
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de regular o botão de importação
	 * de excel presente na GUI, bem como o seu funcionamento.
	 * 
	 * @author Tiago Torres
	 */

	public void importAction() {
		if (!set) {
			set = true;
			importExcel.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeMainWindow);
		}
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XLSX File", "*.xlsx"));
		File f = fc.showOpenDialog(null);
		if (f == null)
			return;
		if (excelWindow != null) {
			excelWindow.close();
		}
		try {
			recordList = parseExcel(f);
		} catch (EncryptedDocumentException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("There has been an error Opening your file!");
			alert.setContentText("Your file is encrypted and cannot be loaded.");
			alert.showAndWait();
			e.printStackTrace();
			return;
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("There has been an error opening your file!");
			alert.setContentText("Your file is unreadable.");
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		counting = countTypes(recordList);
		counting = count_user(counting, recordList, userArray, use_rules.isSelected());
		ExcelController exCtrl = new ExcelController(recordList);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("excelView.fxml"));
		try {
			loader.setController(exCtrl);
			Scene scene = new Scene(loader.load(), 800, 600);
			excelWindow = new Stage();
			excelWindow.setMaximized(true);
			excelWindow.setTitle(sheet.getSheetName());
			excelWindow.setScene(scene);
			excelWindow.show();
			scene.getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {
				excelWindow = null;
				sheet = null;
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método auxiliar desenvolvido com o propósito de reconhecer a escolha do
	 * utilizador quanto à representação gráfica utilizada para os valores de
	 * avaliação calculados.
	 * 
	 * @author João Polónio
	 */

	public void avaliarTool() {
		String choice = avaliarTools.getValue();
		if (choice.equals("Textual")) {
			System.out.println("Textual");
			textualAction();
		} else {
			if (choice.equals("Tabular")) {
				System.out.println("Tabular");
				tabularAction();
			} else {
				System.out.println("Gráfica");
				graficoAction();
			}
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o
	 * controlador primário da GUI e o controlador da visualização textual de dados,
	 * criando uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see TextualControllor
	 * @author João Polónio
	 */

	public void textualAction() {
		if (recordList == null) {
			return;
		}
		TextualController textCtrl = new TextualController(counting);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("textualView.fxml"));
		try {
			loader.setController(textCtrl);
			Scene scene = new Scene(loader.load(), 800, 600);
			textualWindow = new Stage();
			textualWindow.setMaximized(false);
			textualWindow.setTitle("Apresentação Textual");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o
	 * controlador primário da GUI e o controlador da visualização tabular de dados,
	 * criando uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see TableController
	 * @author Tiago Torres
	 */

	public void tabularAction() {
		if (sheet == null) {
			return;
		}
		TableController tableCtrl = new TableController(counting);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("tableView.fxml"));
		try {
			loader.setController(tableCtrl);
			Scene scene = new Scene(loader.load(), 200, 200);
			textualWindow = new Stage();
			textualWindow.setMaximized(false);
			textualWindow.setTitle("Apresentação Tabular");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o
	 * controlador primário da GUI e o controlador da visualização gráfica de dados,
	 * criando uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see ChartController
	 * @author José Raposo
	 */

	public void graficoAction() {

		if (recordList == null) {
			return;
		}

		ChartController graphcontrol = new ChartController(counting);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("graphicsView.fxml"));
		try {
			loader.setController(graphcontrol);
			Scene scene = new Scene(loader.load(), 800, 600);
			textualWindow = new Stage();
			textualWindow.setMaximized(false);
			textualWindow.setTitle("Apresentação Gráfica");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o
	 * controlador primário da GUI e o controlador de regras do utilizador, criando
	 * uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see UserRulesController
	 * @author Tiago Torres
	 */

	@FXML
	public void userRulesAction() {
		UserRulesController userRules = new UserRulesController();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("userRules.fxml"));
		try {
			loader.setController(userRules);
			Scene scene = new Scene(loader.load(), 800, 400);
			userWindow = new Stage();
			userWindow.setMaximized(false);
			userWindow.setTitle("Regras de Utilizador");
			userWindow.setScene(scene);
			userWindow.showAndWait();
			userArray = userRules.getArray();
			counting = count_user(counting, recordList, userArray, use_rules.isSelected());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void switchToSecondary() throws IOException {
	 * App.setRoot("secondary"); }
	 */
}
