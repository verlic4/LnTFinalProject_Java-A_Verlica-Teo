package views;

import controller.PuddingController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class DeletePage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label namaMenuLabel;
    private ComboBox<String> puddingComboBox;
    private Button deleteBtn;

    public DeletePage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        setEventHandlers();
    }

    private void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 500, 300);
        
        formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(20);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20));

        namaMenuLabel = new Label("Nama Pudding:");
        namaMenuLabel.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        puddingComboBox = new ComboBox<>();
        puddingComboBox.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-font-size: 12px;-fx-font-weight: bold; -fx-text-fill: darkred;");

        List<String> puddings = PuddingController.getNamaPuddings();
        puddingComboBox.setItems(FXCollections.observableArrayList(puddings));
    }

    private void setLayout() {
        formGrid.add(namaMenuLabel, 0, 0);
        formGrid.add(puddingComboBox, 1, 0);
        formGrid.add(deleteBtn, 1, 1);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        deleteBtn.setOnAction(event -> {
            String pudding = puddingComboBox.getValue();

            boolean deleted = PuddingController.deletePudding(pudding);

            if (deleted) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Delete Failed.");
	            alert.setHeaderText("Try again.");
	            alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
