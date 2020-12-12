package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Backend.EvalType;
import Backend.TableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador responsável pela representação tabular das avaliações finais devolvidas pelo controlador principal.
 * 
 * @see      PrimaryController
 * @see		 TableEntry
 * @author   Tiago Torres
 */

public class TableController implements Initializable {
	
	@FXML
	private TableView<TableEntry> table;
	private static HashMap<EvalType, Integer> count;
	
	public TableController(HashMap<EvalType, Integer> count) {
		this.count = count;
	}

	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   Tiago Torres
	  */
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<TableEntry, EvalType> type = new TableColumn<>("Type");
		TableColumn<TableEntry, Integer> pmd_count = new TableColumn<>("PMD");
		TableColumn<TableEntry, Integer> plasma_count = new TableColumn<>("iPlasma");
		TableColumn<TableEntry, Integer> user_count = new TableColumn<>("Custom Rules");
		
		type.setCellValueFactory(new PropertyValueFactory<TableEntry, EvalType>("type"));
		pmd_count.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("pmd_count"));
		plasma_count.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("iPlasma_count"));
		user_count.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("user_count"));
		
		table.getColumns().setAll(type, pmd_count, plasma_count);
		EvalType[] list = {EvalType.USER_DCI, EvalType.USER_DII, EvalType.USER_ADCI, EvalType.USER_ADII};
		boolean found = false;
		for (EvalType e : list) {
			found = found || count.containsKey(e);
		}
		
		if (found)
			table.getColumns().add(user_count);
		
		addItems();
		
	}

	/**
	  * Método auxiliar da classe responsável por adicionar na tabela entradas tabulares (TableEntry) com os resultados de cada uma das avaliações.
	  * 
	  * @author   Tiago Torres
	  */
	
	private void addItems() {
		TableEntry dci = new TableEntry("DCI", count.getOrDefault(EvalType.PMD_DCI, 0), count.getOrDefault(EvalType.PLASMA_DCI, 0), count.getOrDefault(EvalType.USER_DCI, 0));
		TableEntry dii = new TableEntry("DII", count.getOrDefault(EvalType.PMD_DII, 0), count.getOrDefault(EvalType.PLASMA_DII, 0), count.getOrDefault(EvalType.USER_DII, 0));
		TableEntry adci = new TableEntry("ADCI", count.getOrDefault(EvalType.PMD_ADCI, 0), count.getOrDefault(EvalType.PLASMA_ADCI, 0), count.getOrDefault(EvalType.USER_ADCI, 0));
		TableEntry adii = new TableEntry("ADII", count.getOrDefault(EvalType.PMD_ADII, 0), count.getOrDefault(EvalType.PLASMA_ADII, 0), count.getOrDefault(EvalType.USER_ADII, 0));
		ObservableList<TableEntry> list = FXCollections.observableArrayList(dci, dii, adci, adii);
		table.getItems().setAll(list);
	}

}
