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
	private Integer plasma_dci = 0;
	private Integer plasma_dii = 0;
	private Integer plasma_adci = 0;
	private Integer plasma_adii = 0;
	
	private Integer pmd_dci = 0;
	private Integer pmd_dii = 0;
	private Integer pmd_adci = 0;
	private Integer pmd_adii = 0;
	
	private Integer user_dci = 0;
	private Integer user_dii = 0;
	private Integer user_adci = 0;
	private Integer user_adii = 0;
	
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
		
		XYChart.Series<String, Number> plasma_set = new XYChart.Series<String, Number>();
		plasma_set.setName("iPlasma");
		
		plasma_set.getData().add(new XYChart.Data<String, Number>("DCI", plasma_dci));
		plasma_set.getData().add(new XYChart.Data<String, Number>("DII", plasma_dii));
		plasma_set.getData().add(new XYChart.Data<String, Number>("ADCI", plasma_adci));
		plasma_set.getData().add(new XYChart.Data<String, Number>("ADII", plasma_adii));
		
		XYChart.Series<String, Number> pmd_set = new XYChart.Series<String, Number>();
		pmd_set.setName("PMD");
		
		pmd_set.getData().add(new XYChart.Data<String, Number>("DCI", pmd_dci));
		pmd_set.getData().add(new XYChart.Data<String, Number>("DII", pmd_dii));
		pmd_set.getData().add(new XYChart.Data<String, Number>("ADCI", pmd_adci));
		pmd_set.getData().add(new XYChart.Data<String, Number>("ADII", pmd_adii));
		
		XYChart.Series<String, Number> user_set = new XYChart.Series<String, Number>();
		user_set.setName("User Rules");
		
		user_set.getData().add(new XYChart.Data<String, Number>("DCI", user_dci));
		user_set.getData().add(new XYChart.Data<String, Number>("DII", user_dii));
		user_set.getData().add(new XYChart.Data<String, Number>("ADCI", user_adci));
		user_set.getData().add(new XYChart.Data<String, Number>("ADII", user_adii));
		
		barChart.getData().addAll(plasma_set, pmd_set);
		
		EvalType[] list = {EvalType.USER_DCI, EvalType.USER_DII, EvalType.USER_ADCI, EvalType.USER_ADII};
		boolean found = false;
		for (EvalType e : list) {
			found = found || data.containsKey(e);
		}
		
		if (found)
			barChart.getData().add(user_set);
		
	}

	private void getValues() {
		plasma_dci = data.getOrDefault(EvalType.PLASMA_DCI, 0);
		plasma_dii = data.getOrDefault(EvalType.PLASMA_DII, 0);
		plasma_adci = data.getOrDefault(EvalType.PLASMA_ADCI, 0);
		plasma_adii = data.getOrDefault(EvalType.PLASMA_ADII, 0);
		
		pmd_dci = data.getOrDefault(EvalType.PMD_DCI, 0);
		pmd_dii = data.getOrDefault(EvalType.PMD_DII, 0);
		pmd_adci = data.getOrDefault(EvalType.PMD_ADCI, 0);
		pmd_adii = data.getOrDefault(EvalType.PMD_ADII, 0);
		
		user_dci = data.getOrDefault(EvalType.USER_DCI, 0);
		user_dii = data.getOrDefault(EvalType.USER_DII, 0);
		user_adci = data.getOrDefault(EvalType.USER_ADCI, 0);
		user_adii = data.getOrDefault(EvalType.USER_ADII, 0);
	}
    
}
