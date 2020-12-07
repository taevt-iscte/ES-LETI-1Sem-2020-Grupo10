package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TextualController implements Initializable {
	
	private HashMap<EvalType, Integer> recordMap;
	@FXML private Label plasma_dci;
	@FXML private Label plasma_dii;
	@FXML private Label plasma_adci;
	@FXML private Label plasma_adii;
	@FXML private Label pmi_dci;
	@FXML private Label pmi_dii;
	@FXML private Label pmi_adci;
	@FXML private Label pmi_adii;
	
	public TextualController(HashMap<EvalType, Integer> recordMap) {
		this.recordMap = recordMap;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		plasma_dci.setText((recordMap.getOrDefault(EvalType.PLASMA_DCI, 0)).toString());
		plasma_dii.setText((recordMap.getOrDefault(EvalType.PLASMA_DII, 0)).toString());
		plasma_adci.setText((recordMap.getOrDefault(EvalType.PLASMA_ADCI, 0)).toString());
		plasma_adii.setText((recordMap.getOrDefault(EvalType.PLASMA_ADII, 0)).toString());
		
		pmi_dci.setText((recordMap.getOrDefault(EvalType.PMD_DCI, 0)).toString());
		pmi_dii.setText((recordMap.getOrDefault(EvalType.PMD_DII, 0)).toString());
		pmi_adci.setText((recordMap.getOrDefault(EvalType.PMD_ADCI, 0)).toString());
		pmi_adii.setText((recordMap.getOrDefault(EvalType.PMD_ADII, 0)).toString());
		
	}
}