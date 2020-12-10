package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Backend.EvalType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

/**
 * Controlador responsável pela representação textual das avaliações produzidas pelas ferramentas já existentes, ou das regras desenvolvidas pelo utilizador, dos valores
 * presentes no ficheiro excel utilizado.
 * 
 * @see      PrimaryController
 * @author   José Raposo
 */

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
	@FXML private Label user_label;
	@FXML private Label user_dci;
	@FXML private Label user_dii;
	@FXML private Label user_adci;
	@FXML private Label user_adii;
	@FXML private Line user_seperator;
	
	public TextualController(HashMap<EvalType, Integer> recordMap) {
		this.recordMap = recordMap;
	}

	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   José Raposo
	  */
	
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
		
		EvalType[] list = {EvalType.USER_DCI, EvalType.USER_DII, EvalType.USER_ADCI, EvalType.USER_ADII};
		boolean found = false;
		for (EvalType e : list) {
			found = found || recordMap.containsKey(e);
		}
		
		if (found) {
			user_dci.setText(recordMap.getOrDefault(EvalType.USER_DCI, 0).toString());
			user_dii.setText(recordMap.getOrDefault(EvalType.USER_DII, 0).toString());
			user_adci.setText(recordMap.getOrDefault(EvalType.USER_ADCI, 0).toString());
			user_adii.setText(recordMap.getOrDefault(EvalType.USER_ADII, 0).toString());
		} else {
			user_label.setVisible(false);
			user_dci.setVisible(false);
			user_dii.setVisible(false);
			user_adci.setVisible(false);
			user_adii.setVisible(false);
			user_seperator.setVisible(false);
		}
		
	}
}