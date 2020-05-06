package application;
	
import Components.Arena;
import Components.Player;
import Lighting.Lighting;
import Movement.MouseControls;
import Movement.SmartGroup;
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
	
	private Arena arena;	// Instantiates the Arena class
	private Lighting light;	// Instantiates the Light class
	private Player player;
	private MouseControls mousecontrols;	// Instantiates the mouse controls class
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Camera //
			Camera camera = new PerspectiveCamera(true);
			camera.setNearClip(1000);
			camera.setFarClip(10000);
			camera.translateZProperty().set(-3000);
			
			// Create the group containers //
			SmartGroup game = new SmartGroup();	// Contains all the components of the screen that rotate
			Group root = new Group();	// Contains everything
			root.getChildren().add(game);
			Scene scene = new Scene(root, WIDTH, HEIGHT, true);	// Creates the window
			scene.setFill(Color.BLACK);
			scene.setCamera(camera);
			
			// Add lighting //
			light = new Lighting();	// Instantiate the lighting class
			root = light.addWith(root);
			
			// Create the arena //
			arena = new Arena();	// Instantiate the arena class
			game = arena.addWith(game);
			
			// Add the snake //
			player = new Player();
			player.addBody(0, 0, 0);
			game = player.addWith(game);
			
			// Mouse Controls //
			mousecontrols = new MouseControls(game);
			mousecontrols.setMousePress(scene);
			mousecontrols.setMouseDrag(scene);
			
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
