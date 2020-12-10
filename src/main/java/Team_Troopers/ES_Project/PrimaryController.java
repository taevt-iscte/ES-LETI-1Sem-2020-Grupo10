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
 * Controlador principal, responsável pela gestão backend da GUI, gerindo a utilização de dados, bem como da sua representação ao utilizador.
 * 
 * @see      App
 * @see		 ChartController
 * @see		 TableController
 * @see		 TextualController
 * @author   Tiago Torres, João Polónio, José Raposo
 */

public class PrimaryController implements Initializable {

	@FXML private Button importExcel;
	@FXML private ComboBox<String> avaliarTools;
	@FXML private Button submitButton;
	@FXML private Button avaliarDadosButton;
	@FXML private CheckBox use_rules;
	private static Sheet sheet;

	private Stage excelWindow;
	private boolean set = false;
	private Stage textualWindow;
	private Stage userWindow;
	private ArrayList<ExcelRecord> recordList;
	private HashMap<EvalType, Integer> counting;
	private ArrayList<String> userArray = new ArrayList<>();

	/**
	 * Permite inicializar o controlador assim que o objeto root terminar de processar.
	 * 
	 * @param    location     localização do root object a usar inicializado. 
	 * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	 * @author   João Polónio
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
		recordList = new ArrayList<ExcelRecord>();
		counting = new HashMap<EvalType, Integer>();
		use_rules.selectedProperty().addListener(this::edit_count);
	}

	/**
	 * Permite lidar com o evento de fechar a janela principal do GUI, bem como a consequência de esta ser fechada em outras janelas possivelmente ainda ativas.
	 * 
	 * @param    e			    Evento causado pelo encerramento da janela principal. 
	 * @author   João Polónio
	 */

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
	 * Método auxiliar da classe, responsável por lidar com os valores de avaliação de acordo com a possibilidade de existência de regras ativas do utilizador.
	 * 
	 * @param    observable		Evento causado pelo encerramento da janela principal.
	 * @param	  oldValue			
	 * @param	  newValue			 
	 * @author   Tiago Torres
	 */

	private void edit_count(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		counting = Util.count_user(counting, recordList, userArray, newValue);
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de regular o botão de importação de excel presente na GUI, bem como o seu funcionamento.
	 * 
	 * @author   Tiago Torres
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
			recordList = Util.parseExcel(f);
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
		counting = Util.countTypes(recordList);
		counting = Util.count_user(counting, recordList, userArray, use_rules.isSelected());
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
	 * Método auxiliar desenvolvido com o propósito de reconhecer a escolha do utilizador quanto à representação gráfica utilizada para os valores de avaliação
	 * calculados.
	 * 
	 * @author   João Polónio
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
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o controlador primário da GUI e o controlador da visualização textual de dados, criando
	 * uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see	  TextualControllor
	 * @author   João Polónio
	 */

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
			textualWindow.setTitle("Apresentação Textual");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o controlador primário da GUI e o controlador da visualização tabular de dados, criando
	 * uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see	  TableController
	 * @author   Tiago Torres
	 */

	public void tabularAction() {
		if(sheet == null) {
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
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o controlador primário da GUI e o controlador da visualização gráfica de dados, criando
	 * uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see	  ChartController
	 * @author   José Raposo
	 */

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
			textualWindow.setTitle("Apresentação Gráfica");
			textualWindow.setScene(scene);
			textualWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método auxiliar desenvolvido com o propósito de gerar uma ponte entre o controlador primário da GUI e o controlador de regras do utilizador, criando
	 * uma janela representativa, bem como chamando o seu controlador.
	 * 
	 * @see	  UserRulesController
	 * @author   Tiago Torres
	 */

	@FXML public void userRulesAction() {
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
			counting = Util.count_user(counting, recordList, userArray, use_rules.isSelected());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void switchToSecondary() throws IOException {
	 * App.setRoot("secondary"); }
	 */
}
