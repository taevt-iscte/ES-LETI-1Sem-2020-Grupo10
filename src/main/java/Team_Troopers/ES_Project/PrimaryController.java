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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrimaryController implements Initializable {

	@FXML private Button importExcel;
	@FXML private ComboBox<String> avaliarTools;
	@FXML private Button submitButton;
	private static Sheet sheet;

	private Stage excelWindow;
	private boolean set = false;
	private Stage textualWindow;
	private ArrayList<ExcelRecord> recordList;
	private HashMap<EvalType, Integer> counting;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
		recordList = new ArrayList<ExcelRecord>();
		counting = new HashMap<EvalType, Integer>();
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
			return;
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("There has been an error opening your file!");
			alert.setContentText("Your file is unreadable.");
			alert.showAndWait();
			e.printStackTrace();
			return;
		}
		getRecordList();
		countTypes();
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

	private void countTypes() {
		recordList.forEach(record -> {
			counting.put(record.getEval(), counting.getOrDefault(record.getEval(), 0)+1);
		});
	}

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

	public void textualAction() {
		if(recordList == null) {
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
			textualWindow.setTitle("Ferramenta Textual");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tabularAction() {

	}

	public void graficoAction() {
		if(recordList == null) {
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
			textualWindow.setTitle("Ferramenta Gráfica");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getRecordList() {
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
	}
	/*
	 * private void switchToSecondary() throws IOException {
	 * App.setRoot("secondary"); }
	 */
}
