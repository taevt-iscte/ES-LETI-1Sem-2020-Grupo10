package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Backend.RuleCondition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * Controlador responsável pela regulação da regras desenvolvidas pelo utilizador.
 * 
 * @see      PrimaryController
 * @see		 RuleCondition
 * @see		 Rules
 * @author   João Polónio
 */

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
	
	/**
	  * Permite construir um objeto da classe.
	  * 
	  * @param    list_r12     ArrayList que servirá de veiculo para as métricas e valores lógicos dados pelo utilizador.
	  * @author   João Polónio
	  */
	
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

	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar, neste caso, irá lidar com a seleção e atribuição dos valores recebidos a partir
	  * da janela interativa ao utilizador, lidando com as escolhas dadas pelo mesmo, podendo mais tarde devolver os valores para serem trabalhados.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   João Polónio
	  */

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
	
	/**
	  * Método auxiliar desenvolvido para tornar possível a utilização de lógicas e métricas por parte do utilizador após o botão "+" ser utilizado.
	  * 
	  * @author   João Polónio
	  */
	
	public void plusButtonClicked() {
		combo_Logic_11.setVisible(true);
		combo_Metric_20.setVisible(true);
		combo_Logic_21.setVisible(true);
		combo_Metric_22.setVisible(true);
	}
	
	/**
	  * Método auxiliar desenvolvido para devolver todos os valores admitidos pelo utilizador assim que concluir todas as suas decisões quanto às regras desenvolvidas.
	  * 
	  * @author   João Polónio
	  */
	
	public void submitButtonClicked() {
		
		data.clear();
		data.add(combo_Metric_00.getValue());
		
		if(!combo_Logic_01.getValue().equals("EMPTY") /*|| combo_Logic_01.getSelectionModel().isEmpty()*/) {
			data.add(combo_Logic_01.getValue());
			data.add(combo_Metric_02.getValue());
			if(!combo_Logic_11.getValue().equals("EMPTY") /* || combo_Logic_11.getSelectionModel().isEmpty() */) {
				data.add(combo_Logic_11.getValue());
				data.add(combo_Metric_02.getValue());
				if(!combo_Logic_21.getValue().equals("EMPTY") /*|| combo_Logic_21.getSelectionModel().isEmpty() */) {
					data.add(combo_Logic_21.getValue());
					data.add(combo_Metric_22.getValue());
				}
			}
		}
		combo_Metric_00.getScene().getWindow().hide();

	}
	
	/**
	  * Método auxiliar desenvolvido para ser possível adquirir a informação das regras desenvolvidas pelo utilizador a partir de classes exteriores.
	  * 
	  * @return	  data			Conjunto das regras a serem devolvidas às outras classes.
	  * @author   João Polónio
	  */
	
	public ArrayList<String> getData() {
		return data;		
	}
	
	

}
