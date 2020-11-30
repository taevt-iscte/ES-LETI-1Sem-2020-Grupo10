package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {
	
	private HashMap<EvalType, Integer> data;
	private Integer dci = 0;
	private Integer dii = 0;
	private Integer adci = 0;
	private Integer adii = 0;
	
	@FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    public ChartController(HashMap<EvalType, Integer> data) {
		this.data = data;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getData();
		XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("DCI", dci));
		set1.getData().add(new XYChart.Data("DII", dii));
		set1.getData().add(new XYChart.Data("ADCI", adci));
		set1.getData().add(new XYChart.Data("ADII", adii));
		barChart.getData().addAll(set1);
	}

	private void getData() {
		dci = data.get(EvalType.DCI);
		dii = data.get(EvalType.DII);
		adci = data.get(EvalType.ADCI);
		adii = data.get(EvalType.ADII);
	}
    
    
	/**
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
	
	**/
}
