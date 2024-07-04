package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("Mainscene.fxml"));
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			Image icon = new Image("file:C:\\Users\\dell\\Documents\\login\\src\\application\\images\\logo-ensah.png");

	        // Set the icon for the application window
	        primaryStage.getIcons().add(icon);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
