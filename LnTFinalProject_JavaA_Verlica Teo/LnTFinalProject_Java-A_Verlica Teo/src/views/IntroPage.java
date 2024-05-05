package views;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IntroPage {
	//stage scene
	protected Stage stage;
	protected Scene scene;
	
	//layout manager
	protected BorderPane rootNode;
	protected VBox vbox;
	
	//components
	protected Label titleText;
	protected Button enterBtn;
	
	//inisialisasi
	protected void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		
		//create dan style components
		String fontUrl = new String("https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Sedan+SC&family=Signika:wght@300..700&display=swap");
		rootNode.getStylesheets().add(fontUrl);
		
		titleText = new Label("Database PT Pudding");
		titleText.setStyle("-fx-font-size: 28; -fx-font-family: 'Signika'; -fx-text-fill: black;");
		
		enterBtn = new Button("Enter");
		enterBtn.setStyle("-fx-background-color: blue; -fx-border-color: #00b34b; -fx-font-size: 18px; -fx-font-family: 'Signika'; -fx-text-fill: white;");
		
		EventHandler<MouseEvent> introEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				HomePage homePage = new HomePage(stage); //intro ke home
		        stage.setScene(homePage.getScene());
			}
		};
		enterBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, introEvent);	
	}
	
	
	protected void layout() {
		VBox centerBox = new VBox(20);
		centerBox.getChildren().addAll(titleText, enterBtn);
	    centerBox.setAlignment(Pos.CENTER);
	    rootNode.setCenter(centerBox);
	}
	
	// scene
    public Scene getScene() {
        return this.scene;
    }
    
    public IntroPage(Stage stage) {
    	init();
    	layout();
    	this.stage = stage;
    	this.stage.setTitle("PT Pudding");
    }


}
