package Team_Troopers.ES_Project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Backend.RuleCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controlador responsável por gerir as regras desenvolvidas pelo utilizador e como estas interagem com as multiplas formas de representar as avaliações produzidas pelas
 * mesmas.
 * 
 * @see      PrimaryController
 * @author   Tiago Torres
 */

public class UserRulesController implements Initializable {
	
	@FXML private TableView<RuleCondition> table = new TableView<>();
	@FXML private TableColumn<RuleCondition, String> metric_col = new TableColumn<>("Métrica");
	@FXML private TableColumn<RuleCondition, String> operator_col = new TableColumn<>("Sinal");
	@FXML private TableColumn<RuleCondition, Integer> value_col = new TableColumn<>("Valor");
	@FXML ComboBox<String> metricInput;
	@FXML ComboBox<String> operatorInput;
	@FXML TextField valueInput;
	@FXML Button addButton;
	@FXML Button deleteButton;
	@FXML HBox hBox = new HBox();
	private RulesController textCtrl;
	
	private Stage rulesWindow;
	
	ObservableList<RuleCondition> list = FXCollections.observableArrayList(
			
			new RuleCondition("CYCLO", ">", 5)
	
	);
	
	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   Tiago Torres
	  */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		metric_col.setCellValueFactory(new PropertyValueFactory<RuleCondition, String>("metric"));
		operator_col.setCellValueFactory(new PropertyValueFactory<RuleCondition, String>("operator"));
		value_col.setCellValueFactory(new PropertyValueFactory<RuleCondition, Integer>("value"));
		
		metricInput.getItems().addAll("LOC", "CYCLO", "ATFD", "LAA");
		operatorInput.getItems().addAll(">", "<");
		hBox.setPadding(new Insets(10, 10, 10, 10));
		
		table.setItems(list);
		
	}
	
	/**
	  * Processa a ação causada ao clicar no botão na GUI para adicionar uma nova regra.
	  * 
	  * @author   Tiago Torres
	  */
	
	public void addButtonAction() {
		RuleCondition rl = new RuleCondition();
		rl.setMetric(metricInput.getValue());
		rl.setOperator(operatorInput.getValue());
		rl.setValue(Long.parseLong(valueInput.getText()));
		table.getItems().add(rl);
		valueInput.clear();
	}
	
	/**
	  * Processa a ação causada ao clicar no botão na GUI para eliminar uma das regras desenhadas pelo utilizador.
	  * 
	  * @author   Tiago Torres
	  */
	
	public void deleteButtonAction() {
		ObservableList<RuleCondition> ruleCondSelected;
		ObservableList<RuleCondition> allRulesCond;
		allRulesCond = table.getItems();
		ruleCondSelected = table.getSelectionModel().getSelectedItems();
		
		ruleCondSelected.forEach(allRulesCond::remove);
		
	}
	
	/**
	  * Processa a ação causada ao clicar no botão na GUI para avançar na próxima janela programada no desenvolvimento de regras.
	  * 
	  * @author   Tiago Torres
	  */
	
	public void nextStepAction() {
		ArrayList<RuleCondition> list_rl = new ArrayList<RuleCondition>(table.getItems());
		textCtrl = new RulesController(list_rl);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("rulesConclusionView.fxml"));
		try {
			loader.setController(textCtrl);
			Scene scene = new Scene(loader.load(), 800, 600);
			rulesWindow = new Stage();
			rulesWindow.setMaximized(false);
			rulesWindow.setTitle("Emparelhamento de Condições");
			rulesWindow.setScene(scene);
			rulesWindow.showAndWait();
			addButton.getScene().getWindow().hide();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * Método auxiliar da classe que devolve uma lista vazia caso o textCtrl estiver vazio ou, caso contrário, adquire a informação.
	  * 
	  * @author   Tiago Torres
	  */
	
	public ArrayList<String> getArray() {
		if (textCtrl == null)
			return new ArrayList<String>();
		else
			return textCtrl.getData();
	}

}
