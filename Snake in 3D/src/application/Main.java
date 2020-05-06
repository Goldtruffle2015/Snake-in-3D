package application;
	
import Components.Arena;
import Components.Player;
import Components.Target;
import GameLoop.GameLoop;
import Lighting.Lighting;
import Movement.MouseControls;
import Movement.SmartGroup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	// -- Attributes -- //
	private static final int WIDTH = 1150;	// Sets the width of the screen
	private static final int HEIGHT = 600;	// Sets the height of the screen
	
	private static Arena arena;	// Instantiates the Arena class
	private static Lighting light;	// Instantiates the Light class
	private static Player player;	// Instantiates the snake
	private static Target target;	// Instantiates the target
	private static MouseControls mousecontrols;	// Instantiates the mouse controls class
	private static GameLoop gameLoop;
	
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
			
			// Add the target //
			target = new Target();
			game = target.addWith(game);
			
			// Mouse Controls //
			mousecontrols = new MouseControls(game);
			mousecontrols.setMousePress(scene);
			mousecontrols.setMouseDrag(scene);
			
			// Keyboard inputs //
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
				switch (event.getCode()) {
					case W:	// Positive Z-Axis
						player.setXVel(0);
						player.setYVel(0);
						player.setZVel(1);
						break;
					case A:	// Negative X-Axis
						player.setXVel(-1);
						player.setYVel(0);
						player.setZVel(0);
						break;
					case S:	// Negative Z-Axis
						player.setXVel(0);
						player.setYVel(0);
						player.setZVel(-1);
						break;
					case D:	// Positive X-Axis
						player.setXVel(1);
						player.setYVel(0);
						player.setZVel(0);
						break;
					case E:	// Negative Y-Axis
						player.setXVel(0);
						player.setYVel(-1);
						player.setZVel(0);
						break;
					case Q:	// Positive Y-Axis
						player.setXVel(0);
						player.setYVel(1);
						player.setZVel(0);
						break;
					default:
						break;
				}
			});
			
			primaryStage.setTitle("Snake");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Game Loop //
			gameLoop = new GameLoop(6);	// Instantiate the game loop
			gameLoop.setLastTime(System.nanoTime());
			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					gameLoop.calcDelta(now);
					if (gameLoop.getDelta() > 1) {
						gameLoop.setDelta(gameLoop.getDelta() - 1);
						// Update //
						player.Move();
					}
					// Render //
				}
			};
			timer.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
