package views;

import java.util.List;

import controller.PuddingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Pudding;

public class HomePage {
    // declare attributes
    protected Stage stage;
    protected Scene scene;

    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox buttonHBox;
    protected Button editBtn, deleteBtn, insertBtn;
    protected TableView<Pudding> tableView;
    protected ObservableList<Pudding> puddingList;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);
       
        List<Pudding> allPuddings = PuddingController.getAllPuddings();
        puddingList = FXCollections.observableArrayList(allPuddings);
        
        tableView = new TableView<>();
        
        // table columns
        TableColumn<Pudding, String> namaColumn = new TableColumn<>("Nama Pudding");
        namaColumn.setCellValueFactory(cellData -> cellData.getValue().namaPuddingProperty());
        namaColumn.setPrefWidth(300);
        
        TableColumn<Pudding, String> hargaColumn = new TableColumn<>("Harga Pudding");
        hargaColumn.setCellValueFactory(cellData -> cellData.getValue().hargaPuddingProperty());
        hargaColumn.setPrefWidth(200);
        
        TableColumn<Pudding, String> stokColumn = new TableColumn<>("Stok Pudding");
        stokColumn.setCellValueFactory(cellData -> cellData.getValue().stokPuddingProperty());
        stokColumn.setPrefWidth(100);
        
        tableView.getColumns().addAll(namaColumn, hargaColumn, stokColumn);
        tableView.setItems(puddingList);
        
        insertBtn = new Button("Insert");
        insertBtn.setStyle("-fx-background-color: #a5c5bb; -fx-border-color: white; "
        		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold;");
        
        editBtn = new Button("Update");
        editBtn.setStyle("-fx-background-color: #d3b8fd; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold;");
        
        deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: #ff0000; -fx-border-color: white; "
        		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold;");
        
        EventHandler<MouseEvent> redirectInsert = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				InsertPage newpage = new InsertPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        insertBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectInsert);
        
        EventHandler<MouseEvent> redirectUpdate = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				UpdatePage newpage = new UpdatePage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        editBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectUpdate);
        
        EventHandler<MouseEvent> redirectDelete = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DeletePage newpage = new DeletePage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectDelete);
    }
    
    //set layout
    protected void setLayout() {
        vbox = new VBox(8);
        vbox.getChildren().addAll(tableView);
        
        buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(insertBtn, editBtn, deleteBtn);

        rootNode.setCenter(vbox);
        rootNode.setBottom(buttonHBox);
    }

    // scene
    protected Scene getScene() {
        return this.scene;
    }

    public HomePage(Stage stage) {
        init();
        setLayout();
        this.stage = stage;
        this.stage.setTitle("Home Page");
    }
}
