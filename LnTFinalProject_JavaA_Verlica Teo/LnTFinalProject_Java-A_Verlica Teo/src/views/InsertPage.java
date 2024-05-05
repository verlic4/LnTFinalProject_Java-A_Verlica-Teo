package views;

import controller.PuddingController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Pudding;

public class InsertPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label namaLabel, hargaLabel, stokLabel;
    private TextField namaField, hargaField, stokField;
    private Button insertBtn;

    public InsertPage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        setEventHandlers();
    }

    private void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 500, 00);
        
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
        stokLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        namaField = new TextField();
        hargaField = new TextField();
        stokField = new TextField();

        insertBtn = new Button("Insert");
        insertBtn.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;-fx-text-fill: darkgreen;");
    }

    private void setLayout() {
        formGrid.add(namaLabel, 0, 0);
        formGrid.add(namaField, 1, 0);
        formGrid.add(hargaLabel, 0, 1);
        formGrid.add(hargaField, 1, 1);
        formGrid.add(stokLabel, 0, 2);
        formGrid.add(stokField, 1, 2);
        formGrid.add(insertBtn, 1, 3);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        insertBtn.setOnAction(event -> {
            String nama = namaField.getText();
            String harga = hargaField.getText();
            String stok = stokField.getText();

            if (nama.isEmpty() || harga.isEmpty() || stok.isEmpty()) {
                System.out.println("Fill the fields");
                return;
            }

            Pudding newPudding = new Pudding(nama, Integer.parseInt(harga),Integer.parseInt(stok));
            boolean inserted = PuddingController.insertPudding(newPudding);
            
            //utk redirect to hp
            if (inserted) {
            	HomePage homepage = new HomePage(stage); 
            	Scene homepageScene = homepage.getScene()
;            	stage.setScene(homepageScene);
//            	stage.setScene(homepage.getScene());
	            stage.show();
            } else {
                System.out.println("Insert fail");
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
