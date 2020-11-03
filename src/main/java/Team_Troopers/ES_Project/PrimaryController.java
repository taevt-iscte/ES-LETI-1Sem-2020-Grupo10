package Team_Troopers.ES_Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrimaryController implements Initializable {

	
	@FXML private Button importExcel;
	@FXML private ComboBox<String> avaliarTools;
	private Sheet sheet;
	private Stage excelWindow;
	private boolean set = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
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
		ExcelController exCtrl = new ExcelController(sheet);
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
			scene.getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {excelWindow = null; sheet = null;});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
    
    /*private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }*/
}
