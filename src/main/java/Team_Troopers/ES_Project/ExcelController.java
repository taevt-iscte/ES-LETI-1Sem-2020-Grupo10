package Team_Troopers.ES_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Backend.ExcelRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador responsável pela representação tabular dos valores vindos do excel, permitindo ao utilizador não só importar os valores do ficheiro para a aplicação, 
 * bem como receber uma visualização otimizada e rápida dos mesmos.
 * 
 * @see      PrimaryController
 * @author   Tiago Torres
 */

public class ExcelController implements Initializable {

	@FXML
	private TableView<ExcelRecord> table;
	private static ArrayList<ExcelRecord> recordList;
	
	 /**
	  * Permite construir um objeto da classe.
	  * 
	  * @param    recordList     ArrayList com valores da classe ExcelRecord, contendo cada um informação relativa a uma linha do excel lido.
	  * @author   Tiago Torres
	  */

	public ExcelController(ArrayList<ExcelRecord> recordList) {
		ExcelController.recordList = recordList;
	}

	/**
	  * Permite inicializar o controlador assim que o objeto root terminar de processar.
	  * 
	  * @param    location     localização do root object a usar inicializado. 
	  * @param    resources    localização dos recursos a serem utilizados para localizar o root object.
	  * @author   Tiago Torres
	  */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addColumns();
/**
		table.setRowFactory(tv -> new TableRow<ExcelRecord>() {
			@Override
			protected void updateItem(ExcelRecord er, boolean empty) {
				super.updateItem(er, empty);
				if (er == null)
					return;
				switch (er.getEval()) {
				case DCI:
					setStyle("-fx-background-color: #649568");
					break;
				case DII:
					setStyle("-fx-background-color: #EFFD5F");
					break;
				case ADII:
					setStyle("-fx-background-color: #FF8B3D");
					break;
				case ADCI:
					setStyle("-fx-background-color: #7AD7F0");
					break;
				}
			}
		});
**/
		table.getItems().setAll(FXCollections.observableArrayList(recordList));
//		table.autosize();
	}
	
	 /**
	  * Método auxiliar da classe, lançado ao inicializar o controlador. 
	  * Permite construir a tabela principal a ser devolvida com as colunas necessárias para apresentar todos os tipos de dados adquiridos.
	  * 
	  * @author   Tiago Torres
	  */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addColumns() {
		TableColumn id = new TableColumn<>("MethodID");
		id.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("id"));
		TableColumn package_ = new TableColumn<>("package");
		package_.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("package_"));
		TableColumn class_ = new TableColumn<>("class");
		class_.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("class_"));
		TableColumn method = new TableColumn<>("method");
		method.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("method"));
		TableColumn loc = new TableColumn<>("LOC");
		loc.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("loc"));
		TableColumn cyclo = new TableColumn<>("CYCLO");
		cyclo.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("cyclo"));
		TableColumn atfd = new TableColumn<>("ATFD");
		atfd.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("atfd"));
		TableColumn laa = new TableColumn<>("LAA");
		laa.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("laa"));
		TableColumn is_long_method = new TableColumn<>("is_long_method");
		is_long_method.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("is_long_method"));
		TableColumn iPlasma = new TableColumn<>("iPlasma");
		iPlasma.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("iPlasma"));
		TableColumn pmd = new TableColumn<>("PMD");
		pmd.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("pmd"));
		TableColumn is_feature_envy = new TableColumn<>("is_feature_envy");
		is_feature_envy.setCellValueFactory(new PropertyValueFactory<ExcelRecord, Integer>("is_feature_envy"));
		table.getColumns().setAll(id, package_, class_, method, loc, cyclo, atfd, laa, is_long_method, iPlasma, pmd,
				is_feature_envy);
	}

}
