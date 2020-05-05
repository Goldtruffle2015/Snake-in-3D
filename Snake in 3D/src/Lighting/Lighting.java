package Lighting;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Lighting {
	// -- Attributes -- //
	private static AmbientLight light;	// Initializes the light source variable
	
	// -- Constructor -- //
	public Lighting() {
		this.light = new AmbientLight();	// Instantiates the light source
		this.light.setColor(Color.WHITE);
	}
	
	public Group addWith(Group root) {	// Adds lightsource to root
		root.getChildren().add(this.light);
		return root;
	}
}
