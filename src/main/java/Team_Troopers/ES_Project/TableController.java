package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable {
	
	@FXML
	private TableView<TableEntry> table;
	private static HashMap<EvalType, Integer> count;
	
	public TableController(HashMap<EvalType, Integer> count) {
		TableController.count = count;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<TableEntry, EvalType> type = new TableColumn<>("Type");
		TableColumn<TableEntry, Integer> pmd_count = new TableColumn<>("PMD");
		TableColumn<TableEntry, Integer> plasma_count = new TableColumn<>("iPlasma");
		
		type.setCellValueFactory(new PropertyValueFactory<TableEntry, EvalType>("type"));
		pmd_count.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("pmd_count"));
		plasma_count.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("iPlasma_count"));
		
		table.getColumns().setAll(type, pmd_count, plasma_count);
		
		addItems();
		
		table.autosize();
		
	}

	private void addItems() {
		TableEntry dci = new TableEntry("DCI", count.get(EvalType.PMD_DCI), count.get(EvalType.PLASMA_DCI));
		TableEntry dii = new TableEntry("DII", count.get(EvalType.PMD_DII), count.get(EvalType.PLASMA_DII));
		TableEntry adci = new TableEntry("ADCI", count.get(EvalType.PMD_ADCI), count.get(EvalType.PLASMA_ADCI));
		TableEntry adii = new TableEntry("ADII", count.get(EvalType.PMD_ADII), count.get(EvalType.PLASMA_ADII));
		ObservableList<TableEntry> list = FXCollections.observableArrayList(dci, dii, adci, adii);
		table.getItems().setAll(list);
	}

}
