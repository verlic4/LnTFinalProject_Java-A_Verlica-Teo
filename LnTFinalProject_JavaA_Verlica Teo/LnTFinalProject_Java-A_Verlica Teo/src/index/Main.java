package index;

import controller.PuddingController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.IntroPage;

public class Main extends Application{
	protected DatabaseConnection db = new DatabaseConnection();
	public PuddingController puddingController = new PuddingController();
	public Main() {
		db.migrateTable();
		puddingController.createDefaultPudding();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		IntroPage in = new IntroPage(arg0);
		arg0.setScene(in.getScene());
		arg0.show();
		
	}
	

}
