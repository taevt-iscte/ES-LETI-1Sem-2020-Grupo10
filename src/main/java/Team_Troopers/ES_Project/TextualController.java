package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TextualController implements Initializable {
	
	private HashMap<EvalType, Integer> recordMap;
	private Integer dci_count = 0;
	private Integer dii_count = 0;
	private Integer adci_count = 0;
	private Integer adii_count = 0;
	@FXML private Label dci_Label;
	@FXML private Label dii_Label;
	@FXML private Label adci_Label;
	@FXML private Label adii_Label;
	@FXML private PieChart pieChart;
	
	public TextualController(HashMap<EvalType, Integer> recordMap) {
		this.recordMap = recordMap;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recordMapReading();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("DCI", 13),
				new PieChart.Data("DII", 25),
				new PieChart.Data("ADCI", 10),
				new PieChart.Data("ADII", 22)
				);
		pieChart.setData(pieChartData);
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
		
		changeLabelContent();
	}
	
	
	private void changeLabelContent() {
		dci_Label.setText("DCI: " + dci_count.toString());
		dii_Label.setText("DII: " + dii_count.toString());
		adci_Label.setText("ADCI: " + adci_count.toString());
		adii_Label.setText("ADII: " + adii_count.toString());
	}
	
}