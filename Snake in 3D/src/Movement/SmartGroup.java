package Movement;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class SmartGroup extends Group {
	// -- Attributes -- //
	Rotate r;
	Transform t = new Rotate();
	
	public void rotateByX(int a) {	// Handles rotations along X-axis
		r = new Rotate(a, Rotate.X_AXIS);	// Creates a rotation 
		t = t.createConcatenation(r);	// Adds r to sequence of transformations to perform
		this.getTransforms().clear();   // Clears all previous transformations
		this.getTransforms().add(t);	// Executes transformations in t
	}
	
	public void rotateByY(int a) {	// Handles rotations along Y-axis
		r = new Rotate(a, Rotate.Y_AXIS);	// Creates a rotation
		t = t.createConcatenation(r);	// Adds r to sequence of transformations to perform
		this.getTransforms().clear();	// Clears all previous transformations
		this.getTransforms().add(t);	// Executes transformations in t
	}
}
