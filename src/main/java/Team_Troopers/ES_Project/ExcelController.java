package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExcelController implements Initializable{

	private static Sheet sheet;
	@FXML private TableView<ExcelRecord> table;
	
	public ExcelController(Sheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addColumns();
		
		 for (int r = 1; r < sheet.getLastRowNum(); r++) {
			getRecordFromRow(sheet.getRow(r));
		}
	}
	
	private void addColumns() {
		TableColumn id = new TableColumn<>("MethodID");
		TableColumn package_ = new TableColumn<>("package");
		TableColumn class_ = new TableColumn<>("class");
		TableColumn method = new TableColumn<>("method");
		TableColumn loc = new TableColumn<>("LOC");
		TableColumn cyclo = new TableColumn<>("CYCLO");
		TableColumn atfd = new TableColumn<>("ATFD");
		TableColumn laa = new TableColumn<>("LAA");
		TableColumn is_long_method = new TableColumn<>("is_long_method");
		TableColumn iPlasma = new TableColumn<>("iPlasma");
		TableColumn pmd = new TableColumn<>("PMD");
		TableColumn is_feature_envy = new TableColumn<>("is_feature_envy");
		table.getColumns().setAll(id, package_, class_, method, loc, cyclo, atfd, laa, is_long_method,
				iPlasma, pmd, is_feature_envy);
	}


	private ExcelRecord getRecordFromRow(Row r) {
		return null;
	}

}
