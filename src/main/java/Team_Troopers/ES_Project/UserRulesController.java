package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

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
	
	ObservableList<RuleCondition> list = FXCollections.observableArrayList(
			
			new RuleCondition("CYCLO", ">", 5)
	
	);
	

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
	
	
	public void addButtonAction() {
		RuleCondition rl = new RuleCondition();
		rl.setMetric(metricInput.getValue());
		rl.setOperator(operatorInput.getValue());
		rl.setValue(Long.parseLong(valueInput.getText()));
		table.getItems().add(rl);
		valueInput.clear();
	}
	
	public void deleteButtonAction() {
		ObservableList<RuleCondition> ruleCondSelected;
		ObservableList<RuleCondition> allRulesCond;
		allRulesCond = table.getItems();
		ruleCondSelected = table.getSelectionModel().getSelectedItems();
		
		ruleCondSelected.forEach(allRulesCond::remove);
		
	}
	

}
