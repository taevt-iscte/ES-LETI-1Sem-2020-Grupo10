package Team_Troopers.ES_Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class PrimaryController implements Initializable {

	
	@FXML private Button importExcel;
	@FXML private ComboBox<String> avaliarTools;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		avaliarTools.getItems().addAll("Textual", "Tabular", "Gráfica");
	}
	
	
	
	
	
    
    /*private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }*/
}
