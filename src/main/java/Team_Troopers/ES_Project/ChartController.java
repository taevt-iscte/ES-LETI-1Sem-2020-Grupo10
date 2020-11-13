package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ChartController {
	
	private HashMap<EvalType, Integer> data;
	private Integer dci = 0;
	private Integer dii = 0;
	private Integer adci = 0;
	private Integer adii = 0;
	
	public ChartController(HashMap<EvalType, Integer> data) {
		this.data = data;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		recordMapReading();
	}
	
	private void recordMapReading() {
		if(data.containsKey(EvalType.DCI)) {
			dci = data.get(EvalType.DCI);
			System.out.println("DCI: " + dci);
		}
		
		if(data.containsKey(EvalType.DII)) {
			dii = data.get(EvalType.DII);
			System.out.println("DII: " + dii);
		}
		
		if(data.containsKey(EvalType.ADCI)) {
			adci = data.get(EvalType.ADCI);
			System.out.println("ADCI: " + adci);
		}
		
		if(data.containsKey(EvalType.ADII)) {
			adii = data.get(EvalType.ADII);
			System.out.println("ADII: " + adii);
		}

	}
}
