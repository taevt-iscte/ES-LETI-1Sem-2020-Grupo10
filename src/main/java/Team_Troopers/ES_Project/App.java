package Team_Troopers.ES_Project;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application implements EventHandler<ActionEvent> {

    private static Scene scene;
    private Button importButton;
	private Button avaliarButton;
	private Workbook book;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
    	
    /*	stage.setTitle("Title");
    	
    	importButton = new Button();
    	importButton.setText("Importar Excel");
    	importButton.setOnAction((ActionEvent e) -> {
    		FileChooser fc = new FileChooser();
    		fc.getExtensionFilters().add(new ExtensionFilter("Excel XLSX", "*.xlsx"));
    		File excel = fc.showOpenDialog(stage);
    		if (excel == null)
    			return;
    		System.out.println(excel.getAbsolutePath() + " Loaded...");
    		
    	});
    	
    	avaliarButton = new Button("Avaliar");
    	avaliarButton.setOnAction(this);
    	
    	
    	ChoiceBox<String> choiceBox = new ChoiceBox<>();
    	choiceBox.getItems().addAll("Textual", "Tabular","Gráfica");
    	//default value
    	choiceBox.setValue("Textual");
    	avaliarButton.setOnAction(e -> getChoice(choiceBox));
    	
    	
    	HBox bottomMenu = new HBox();
    	bottomMenu.getChildren().addAll(importButton, choiceBox, avaliarButton);
    	
    	BorderPane borderPane = new BorderPane();
    	borderPane.setBottom(bottomMenu);
    */	
    
    	FXMLLoader loader = new FXMLLoader();	
        loader.setLocation(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        //scene = new Scene(loadFXML("primary"), 640, 480);
    
    	Scene scene = new Scene(root);
    	stage.setTitle("Exceltify");
        stage.setScene(scene);
        stage.setTitle("Exceltify");
        stage.show();
        
    }
    
    @Override 
    public void handle(ActionEvent event) {
    	if(event.getSource() == avaliarButton) {
    		
    	}
    }
    
    private void getChoice(ChoiceBox<String> choiceBox) {
    	if(choiceBox.getValue().equals("Textual")) {
    		
    	}
    }

  static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}