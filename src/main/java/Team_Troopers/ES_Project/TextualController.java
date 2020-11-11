package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.fxml.Initializable;

public class TextualController implements Initializable {
	
	private HashMap<EvalType, Integer> recordMap;
	private Integer dci_count = 0;
	private Integer dii_count = 0;
	private Integer adci_count = 0;
	private Integer adii_count = 0;
	
	public TextualController(HashMap<EvalType, Integer> recordMap) {
		this.recordMap = recordMap;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recordMapReading();
	}
	
	private void recordMapReading() {
		if(recordMap.containsKey(EvalType.DCI)) {
			dci_count = recordMap.get(EvalType.DCI);
			System.out.println("DCI: " + dci_count);
		}
		
		if(recordMap.containsKey(EvalType.DII)) {
			dii_count = recordMap.get(EvalType.DII);
			System.out.println("DII: " + dii_count);
		}
		
		if(recordMap.containsKey(EvalType.ADCI)) {
			adci_count = recordMap.get(EvalType.ADCI);
			System.out.println("ADCI: " + adci_count);
		}
		
		if(recordMap.containsKey(EvalType.ADII)) {
			adii_count = recordMap.get(EvalType.ADII);
			System.out.println("ADII: " + adii_count);
		}
	}
	
}