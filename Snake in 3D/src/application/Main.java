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
	private static Group root;
	private static SmartGroup game;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Camera //
			Camera camera = new PerspectiveCamera(true);
			camera.setNearClip(1000);
			camera.setFarClip(10000);
			camera.translateZProperty().set(-3000);
			
			// Create the group containers //
			game = new SmartGroup();	// Contains all the components of the screen that rotate
			root = new Group();	// Contains everything
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
			game = player.addBody(game, 0, 0, 0);
			
			// Add the target //
			target = new Target();
			game = target.addWith(game);
			
			// Mouse Controls //
			mousecontrols = new MouseControls(game);
			mousecontrols.setMousePress(scene);
			mousecontrols.setMouseDrag(scene);
			
			// Keyboard inputs //
			keyInputs(primaryStage);
			
			primaryStage.setTitle("Snake");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Game Loop //
			gameLoop = new GameLoop(4);	// Instantiate the game loop
			gameLoop.setLastTime(System.nanoTime());
			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					gameLoop.calcDelta(now);
					if (gameLoop.getDelta() > 1) {
						gameLoop.setDelta(gameLoop.getDelta() - 1);
						// Update //
						update(game, primaryStage);
					}
					// Render //
				}
			};
			timer.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keyInputs(Stage stage) {	// Where key inputs happen
		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
				case W:	// Positive Z-Axis
					if (player.getZVel() != -1) {
						player.setXVel(0);
						player.setYVel(0);
						player.setZVel(1);
					}
					break;
				case A:	// Negative X-Axis
					if (player.getXVel() != 1) {
						player.setXVel(-1);
						player.setYVel(0);
						player.setZVel(0);
					}
					break;
				case S:	// Negative Z-Axis
					if (player.getZVel() != 1) {
						player.setXVel(0);
						player.setYVel(0);
						player.setZVel(-1);
					}
					break;
				case D:	// Positive X-Axis
					if (player.getXVel() != -1) {
						player.setXVel(1);
						player.setYVel(0);
						player.setZVel(0);
					}
					break;
				case E:	// Negative Y-Axis
					if (player.getYVel() != 1) {
						player.setXVel(0);
						player.setYVel(-1);
						player.setZVel(0);
					}
					break;
				case Q:	// Positive Y-Axis
					if (player.getYVel() != -1 ) {
						player.setXVel(0);
						player.setYVel(1);
						player.setZVel(0);
					}
					break;
				default:
					break;
			}
		});
	}
	
	public void update(SmartGroup group, Stage stage) {	// Where all updates happen
		// Check snake-target collision //
		if (player.getBody().get(0).getX() == target.getX() &&
				player.getBody().get(0).getY() == target.getY() &&
				player.getBody().get(0).getZ() == target.getZ()) {
			target.reposition();
			group = player.addBody(group, 0, 0, 0);
		}
		
		player.Move();	// Moves the snake
		
		// Boundary collision //
		if (player.getX() < -arena.getLength()/2 || 
				player.getX() > arena.getLength()/2 ||
				player.getY() < -arena.getLength()/2 ||
				player.getY() > arena.getLength()/2 ||
				player.getZ() < -arena.getLength()/2 ||
				player.getZ() > arena.getLength()/2) {
			stage.close();
		}
		
		// Self Collision //
		for (int i=player.getBody().size() - 1;i>0;i--) {
			if (player.getBody().get(0).getX() == player.getBody().get(i).getX() &&
					player.getBody().get(0).getY() == player.getBody().get(i).getY() &&
					player.getBody().get(0).getZ() == player.getBody().get(i).getZ()) {
				stage.close();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
