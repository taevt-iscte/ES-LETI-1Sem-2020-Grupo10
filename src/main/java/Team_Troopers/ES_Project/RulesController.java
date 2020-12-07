package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RulesController implements Initializable {
	
	@FXML ComboBox<String> combo_Metric_00;
	@FXML ComboBox<String> combo_Logic_01;
	@FXML ComboBox<String> combo_Metric_02;
	@FXML ComboBox<String> combo_Logic_11;
	@FXML ComboBox<String> combo_Metric_20; 
	@FXML ComboBox<String> combo_Logic_21;
	@FXML ComboBox<String> combo_Metric_22;
	
	@FXML Button plusButton;
	@FXML Button submitButton;
	
	private ArrayList<ComboBox> combobox_arr = new ArrayList<>();
	private ArrayList<RuleCondition> list_rl;
	private ArrayList<String> data = new ArrayList<>();
	
	
	public RulesController(ArrayList<RuleCondition> list_rl2) {
		
		this.list_rl = list_rl2;
		
		combobox_arr.add(combo_Metric_00);
		combobox_arr.add(combo_Logic_01);
		combobox_arr.add(combo_Metric_02);
		/*combobox_arr.add(combo_Logic_11);
		combobox_arr.add(combo_Metric_20);
		combobox_arr.add(combo_Logic_21);
		combobox_arr.add(combo_Metric_22);*/
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		combo_Logic_01.getItems().addAll("EMPTY", "AND", "OR");
		combo_Logic_11.getItems().addAll("EMPTY", "AND", "OR");
		combo_Logic_21.getItems().addAll("EMPTY", "AND", "OR");
		
		combo_Logic_01.getSelectionModel().selectFirst();
		combo_Logic_11.getSelectionModel().selectFirst();
		combo_Logic_21.getSelectionModel().selectFirst();
		
		combo_Metric_00.getItems().add("EMPTY");
		combo_Metric_02.getItems().add("EMPTY");
		combo_Metric_20.getItems().add("EMPTY");
		combo_Metric_22.getItems().add("EMPTY");
		
		combo_Metric_00.getSelectionModel().selectFirst();
		combo_Metric_02.getSelectionModel().selectFirst();
		combo_Metric_20.getSelectionModel().selectFirst();
		combo_Metric_22.getSelectionModel().selectFirst();
		
		for(int i=0; i < list_rl.size(); i++) {
			String metric_name = list_rl.get(i).getMetric();
			String metric_operator = list_rl.get(i).getOperator();
			Long metric_value = list_rl.get(i).getValue();
			System.out.println(list_rl.get(i));
			combo_Metric_00.getItems().add(metric_name + " " + metric_operator + " " + metric_value);
			combo_Metric_02.getItems().add(metric_name + " " + metric_operator + " " + metric_value);
			combo_Metric_20.getItems().add(metric_name + " " + metric_operator + " " + metric_value);
			combo_Metric_22.getItems().add(metric_name + " " + metric_operator + " " + metric_value);
		}
		
		combo_Logic_11.setVisible(false);
		combo_Metric_20.setVisible(false);
		combo_Logic_21.setVisible(false);
		combo_Metric_22.setVisible(false);
		
	}
	
	public void plusButtonClicked() {
		combo_Logic_11.setVisible(true);
		combo_Metric_20.setVisible(true);
		combo_Logic_21.setVisible(true);
		combo_Metric_22.setVisible(true);
	}
	
	public void submitButtonClicked() {
		
		data.clear();
		data.add(combo_Metric_00.getValue());
		
		if(combo_Logic_01.getValue().equals("EMPTY") /*|| combo_Logic_01.getSelectionModel().isEmpty()*/) {
			System.out.println("STOP");
			return;
		}
		else {
			data.add(combo_Logic_01.getValue());
			data.add(combo_Metric_02.getValue());
			if(combo_Logic_11.getValue().equals("EMPTY") /* || combo_Logic_11.getSelectionModel().isEmpty() */) {
				combo_Metric_00.getScene().getWindow().hide();
				System.out.println("I'm supposed to hide now");
				return;
			}
			else {
				data.add(combo_Logic_11.getValue());
				data.add(combo_Metric_02.getValue());
				if(combo_Logic_21.getValue().equals("EMPTY") /*|| combo_Logic_21.getSelectionModel().isEmpty() */) {
					combo_Metric_00.getScene().getWindow().hide();
					System.out.println("I'm supposed to hide now");
					return;
				}
				else {
					data.add(combo_Logic_21.getValue());
					data.add(combo_Metric_22.getValue());
					combo_Metric_00.getScene().getWindow().hide();
					System.out.println("I'm supposed to hide now");
				}
			}
		}

	}
	
	public ArrayList<String> getData() {
		return data;		
	}
	
	

}
