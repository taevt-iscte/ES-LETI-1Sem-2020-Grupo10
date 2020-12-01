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
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    public ChartController(HashMap<EvalType, Integer> data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getValues();
		
		XYChart.Series<String, Number> set1 = new XYChart.Series<>();
		set1.setName("Resultados Positivos");
		
		set1.getData().add(new XYChart.Data<String, Number>("DCI", dci));
		set1.getData().add(new XYChart.Data<String, Number>("DII", dii));
		set1.getData().add(new XYChart.Data<String, Number>("ADCI", adci));
		set1.getData().add(new XYChart.Data<String, Number>("ADII", adii));
		barChart.getData().addAll(set1);
	}

	private void getValues() {
		dci = data.getOrDefault(EvalType.DCI, 0);
		dii = data.getOrDefault(EvalType.DII, 0);
		adci = data.getOrDefault(EvalType.ADCI, 0);
		adii = data.getOrDefault(EvalType.ADII, 0);
	}
    
}
