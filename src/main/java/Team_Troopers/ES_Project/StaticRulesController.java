package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador responsável pela definição de regras default a fim de serem utilizadas como avaliação padrão quando não existirem regras geradas pelo utilizador.
 * 
 * @see      PrimaryController
 * @author   Tiago Torres
 */

public class StaticRulesController implements Initializable {
	
	@FXML private TextField loc_field;
	@FXML private TextField cyclo_field;
	@FXML private TextField atfd_field;
	@FXML private TextField laa_field;
	@FXML private TextField nofa_field;
	@FXML private Button submit_long;
	@FXML private Button submit_envy;

	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   Tiago Torres
	  */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @return   array		    Array com os valores atuais a serem lidos pelas ferramentas avaliadoras.
	  * @author   Tiago Torres
	  */
	
	public ArrayList<Long> load_LongMethod() {
		ArrayList<Long> array = new ArrayList<Long>();
		try {
		Long loc_value = Long.parseLong(loc_field.getText());
		Long cyclo_value = Long.parseLong(cyclo_field.getText());
		array.add(loc_value);
		array.add(cyclo_value);
		} catch(final NumberFormatException e) {
			System.out.println("Um dos inputs que colocou no Long Method é inválido! Deve ser um número!");
		}
		return array;
	}
	

}
