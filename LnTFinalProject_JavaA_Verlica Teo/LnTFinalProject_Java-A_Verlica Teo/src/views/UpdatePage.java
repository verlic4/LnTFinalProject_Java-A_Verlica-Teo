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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class UpdatePage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label namaLabel, hargaLabel, stokLabel;
    private ComboBox<String> puddingComboBox;
    private TextField hargaField, stokField;
    private Button updateBtn;

    public UpdatePage(Stage stage) {
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

        namaLabel = new Label("Nama:");
        namaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        hargaLabel = new Label("Harga:");
        hargaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        stokLabel = new Label("Stok:");
        stokLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; ");

        puddingComboBox = new ComboBox<>();
        puddingComboBox.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-text-fill: #333333;");
        hargaField = new TextField();
        hargaField.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-text-fill: #333333;");
        stokField = new TextField();
        stokField.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-text-fill: #333333;");
        


        updateBtn = new Button("Update");
        updateBtn.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;-fx-text-fill: purple;");
        
        List<String> puddings = PuddingController.getNamaPuddings();
        puddingComboBox.setItems(FXCollections.observableArrayList(puddings));
    }

    private void setLayout() {
        formGrid.add(namaLabel, 0, 0);
        formGrid.add(puddingComboBox, 1, 0);
        formGrid.add(hargaLabel, 0, 1);
        formGrid.add(hargaField, 1, 1);
        formGrid.add(stokLabel, 0, 2);
        formGrid.add(stokField, 1, 2);
        formGrid.add(updateBtn, 1, 3);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        updateBtn.setOnAction(event -> {
            String namaPudding = puddingComboBox.getValue();
            String harga = hargaField.getText();
            String stok = stokField.getText();

            boolean updated = PuddingController.updatePudding(namaPudding,Integer.parseInt(harga),Integer.parseInt(stok));

            if (updated) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Update Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Something wrong happens.");
	            alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
