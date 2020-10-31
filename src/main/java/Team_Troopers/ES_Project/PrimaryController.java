package Team_Troopers.ES_Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PrimaryController implements Initializable {

	
	@FXML private Button importExcel;
	@FXML private ComboBox<String> avaliarTools;
	private Sheet sheet;
	private ExcelController exCtrl;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
	}
	
	public void importAction() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XLSX File", "*.xlsx"));
		File f = fc.showOpenDialog(null);
		try {
			sheet = WorkbookFactory.create(f).getSheetAt(0);
		} catch (EncryptedDocumentException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("There has been an error Opening your file!");
			alert.setContentText("Your file is encrypted and cannot be loaded.");
			alert.showAndWait();
			e.printStackTrace();
			return ;
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("There has been an error opening your file!");
			alert.setContentText("Your file is unreadable.");
			alert.showAndWait();
			e.printStackTrace();
			return ;
		}
		exCtrl = new ExcelController(sheet);
		
	}
	
	
	
    
    /*private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }*/
}
