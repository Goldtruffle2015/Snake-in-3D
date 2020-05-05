package application;
	
import Components.Arena;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	// -- Attributes -- //
	private static final int WIDTH = 1150;	// Sets the width of the screen
	private static final int HEIGHT = 600;	// Sets the height of the screen
	private Arena arena;	// Instantiates the arena
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Camera //
			Camera camera = new PerspectiveCamera(true);
			camera.setNearClip(1000);
			camera.setFarClip(10000);
			camera.translateZProperty().set(-1500);
			
			// Create the root container //
			Group root = new Group();	// Contains all the components of the screen
			Scene scene = new Scene(root, WIDTH, HEIGHT, true);	// Creates the window
			scene.setFill(Color.BLACK);
			scene.setCamera(camera);
			
			// Create the arena //
			arena = new Arena();
			root = arena.addWith(root);
			
			primaryStage.setTitle("Snake");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
