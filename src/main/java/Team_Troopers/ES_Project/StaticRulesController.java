package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StaticRulesController implements Initializable {
	
	@FXML private TextField loc_field;
	@FXML private TextField cyclo_field;
	@FXML private TextField atfd_field;
	@FXML private TextField laa_field;
	@FXML private TextField nofa_field;
	@FXML private Button submit_long;
	@FXML private Button submit_envy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Long> load_LongMethod() {
		ArrayList<Long> array = new ArrayList<Long>();
		try {
		Long loc_value = Long.parseLong(loc_field.getText());
		Long cyclo_value = Long.parseLong(cyclo_field.getText());
		array.add(loc_value);
		array.add(cyclo_value);
		} catch(final NumberFormatException e) {
			System.out.println("Um dos inputs que colocou no Long Method são inválidos! Deve ser um número!");
		}
		return array;
	}
	

}
