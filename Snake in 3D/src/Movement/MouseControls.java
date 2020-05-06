package Movement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;

public class MouseControls {
	// -- Attributes -- //
	private double anchorX, anchorY;	// Stores the mouse pressed position
	private double anchorAngleX, anchorAngleY;	// Stores the angle of mouse pressed position
	private DoubleProperty angleX, angleY;	// Angle to transform object
	private Rotate xRotate, yRotate;	// Defines rotation object
	
	// -- Constructor -- //
	public MouseControls(SmartGroup group) {
		this.xRotate = new Rotate(0, Rotate.X_AXIS);
		this.yRotate = new Rotate(0, Rotate.Y_AXIS);
		
		this.anchorAngleX = 0;
		this.anchorAngleY = 0;
		
		this.angleX = new SimpleDoubleProperty(0);	// Double that behaves like an object
		this.angleY = new SimpleDoubleProperty(0);	// Double that behaves like an object
		
		group.getTransforms().addAll(this.xRotate, this.yRotate);	// Apply transformations
		
		this.xRotate.angleProperty().bind(this.angleX);	// Redefines angle property when angleX changes
		this.yRotate.angleProperty().bind(this.angleY);	// Redefines angle property when angleY changes
	}
	
	// -- Methods -- //
	public void setMousePress(Scene scene) {
		scene.setOnMousePressed(event -> {
			this.anchorX = event.getSceneX();	// Gets the current x-coordinate
			this.anchorY = event.getSceneY();	// Gets the current y-coordinate
			this.anchorAngleX = this.angleX.get();	// Gets the current x-angle the object was rotated by
			this.anchorAngleY = this.angleY.get();	// Gets the current y-angle the object was rotated by
		});
	}
	
	public void setMouseDrag(Scene scene) {
		scene.setOnMouseDragged(event -> {
			this.angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			this.angleY.set(anchorAngleY + anchorX - event.getSceneX());
		});
	}
}
