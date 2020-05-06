package Components;

import Movement.SmartGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Target {	// This is what the snake eats
	// -- Attributes -- //
	private final int SIZE = 20;	// Defines the size of the target
	private int x, y, z;	// Defines the position if the target
	private Box box;
	
	// -- Constructor -- //
	public Target() {
		this.box = new Box(this.SIZE, this.SIZE, this.SIZE);
		
		this.x = (int) ((Math.random() * 39 - 20) * 20.0 + this.SIZE/2);
		this.y = (int) ((Math.random() * 39 - 20) * 20.0 + this.SIZE/2);
		this.z = (int) ((Math.random() * 39 - 20) * 20.0 + this.SIZE/2);
		
		this.box.translateXProperty().set(this.x);
		this.box.translateYProperty().set(this.y);
		this.box.translateZProperty().set(this.z);
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(Color.RED);
		this.box.setMaterial(mat);
	}
	
	// -- Methods -- //
	public SmartGroup addWith(SmartGroup group) {
		group.getChildren().add(this.box);
		return group;
	}
}
