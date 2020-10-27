package Team_Troopers.ES_Project;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application implements EventHandler<ActionEvent> {

    private static Scene scene;
    private Button importButton;
	private Button avaliarButton;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	stage.setTitle("Title");
    	
    	importButton = new Button();
    	importButton.setText("Importar Excel");
    	importButton.setOnAction(this);
    	
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
    	
   
   
        //scene = new Scene(loadFXML("primary"), 640, 480);
    	Scene scene = new Scene(borderPane, 300, 250);
        stage.setScene(scene);
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