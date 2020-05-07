package Components;

import Movement.SmartGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Body {	// Constructs the actual snake
	// -- Attributes -- //
	private final int SIZE = 20;	// Size of each part of the snake
	private int x, y, z;	// Defines the position of the body
	private Box box;	// Creates the cube
	
	// -- Constructor -- //
	public Body(int x, int y, int z) {
		this.box = new Box(this.SIZE, this.SIZE, this.SIZE);
		this.x = x + this.SIZE/2;	// Set the x-coordinate
		this.y = y + this.SIZE/2;	// Set the y-coordinate
		this.z = z + this.SIZE/2;	// Set the z-coordinate
		this.box.translateXProperty().set(this.x);
		this.box.translateYProperty().set(this.y);
		this.box.translateZProperty().set(this.z);
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(Color.GREEN);
		this.box.setMaterial(mat);
	}
	
	// -- Setter -- //
	public void setX(int x) {
		this.x = x + this.SIZE/2;
	}
	
	public void setY(int y) {
		this.y = y + this.SIZE/2;
	}
	
	public void setZ(int z) {
		this.z = z + this.SIZE/2;
	}
	
	// -- Setter -- //
	public void setPos() {	// Sets the new translation
		this.box.translateXProperty().set(this.x);
		this.box.translateYProperty().set(this.y);
		this.box.translateZProperty().set(this.z);
	}
	
	// -- Getter -- //
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public int getSize() {
		return this.SIZE;
	}
	
	// -- Methods -- //
	public SmartGroup addWith(SmartGroup group) {
		group.getChildren().add(this.box);
		return group;
	}
}
