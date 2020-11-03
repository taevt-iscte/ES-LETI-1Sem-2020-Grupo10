package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javafx.fxml.Initializable;

public class TextualController implements Initializable {
	
	private ArrayList<ExcelRecord> recordList;
	
	public TextualController(ArrayList<ExcelRecord> recordList) {
		this.recordList = recordList;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}