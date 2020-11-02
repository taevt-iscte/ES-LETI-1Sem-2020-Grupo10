package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExcelController implements Initializable {

	private static Sheet sheet;
	@FXML
	private TableView<ExcelRecord> table;
	
	public ExcelController(Sheet sheet) {
		ExcelController.sheet = sheet;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addColumns();
		
		table.setRowFactory(tv -> new TableRow<ExcelRecord>() {
			@Override
			protected void updateItem(ExcelRecord er, boolean empty) {
				super.updateItem(er, empty);
				if (er == null)
					return;
				if (er.isIs_long_method() && er.isIPlasma() && er.isPmd())
					setStyle("-fx-background-color: #649568");
				else if (er.isIs_long_method() && !(er.isIPlasma() && er.isPmd()))
					setStyle("-fx-background-color: #EFFD5F");
				else if (!er.isIs_long_method() && (er.isIPlasma() || er.isPmd()))
					setStyle("-fx-background-color: #FF8B3D");
				else if (!er.isIs_long_method() && !er.isIPlasma() && !er.isPmd())
					setStyle("-fx-background-color: #7AD7F0");
			}
		});
		
		for (int r = 1; r < sheet.getLastRowNum(); r++) {
			table.getItems().add(getRecordFromRow(sheet.getRow(r)));
		}
//		table.autosize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addColumns() {
		TableColumn id = new TableColumn<>("MethodID");
		id.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("id"));
		TableColumn package_ = new TableColumn<>("package");
		package_.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("package_"));
		TableColumn class_ = new TableColumn<>("class");
		class_.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("class_"));
		TableColumn method = new TableColumn<>("method");
		method.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("method"));
		TableColumn loc = new TableColumn<>("LOC");
		loc.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("loc"));
		TableColumn cyclo = new TableColumn<>("CYCLO");
		cyclo.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("cyclo"));
		TableColumn atfd = new TableColumn<>("ATFD");
		atfd.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("atfd"));
		TableColumn laa = new TableColumn<>("LAA");
		laa.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("laa"));
		TableColumn is_long_method = new TableColumn<>("is_long_method");
		is_long_method.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("is_long_method"));
		TableColumn iPlasma = new TableColumn<>("iPlasma");
		iPlasma.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("iPlasma"));
		TableColumn pmd = new TableColumn<>("PMD");
		pmd.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("pmd"));
		TableColumn is_feature_envy = new TableColumn<>("is_feature_envy");
		is_feature_envy.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("is_feature_envy"));
		table.getColumns().setAll(id, package_, class_, method, loc, cyclo, atfd, laa, is_long_method, iPlasma, pmd,
				is_feature_envy);
	}

	private ExcelRecord getRecordFromRow(Row r) {
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
		return new ExcelRecord(id, package_, class_, method, loc, cyclo, atfd, laa, is_long_method, iPlasma, pmd,
				is_feature_envy);
	}

}
