package Components;	// Handles all in-game objects

import Movement.SmartGroup;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Arena {
	// -- Attributes -- //
	private final int length = 800;	// Defines the length of the arena
	private final int radius = 5;	// Defines the radius of the nodes
	private Sphere[] nodes = new Sphere[8];	// Stores nodes
	private Cylinder[] edges = new Cylinder[12];	// Stores the edges
	
	// -- Constructor -- //
	public Arena() {
		createNodes();	// Sets up the nodes
		createEdges();	// Sets up the edges
	}
	
	// -- Methods -- //
	public SmartGroup addWith(SmartGroup root) {	// Adds the pieces of the arena to the root group
		// Add nodes //
		for (int i=0;i<8;i++) {
			root.getChildren().add(this.nodes[i]);
		}
		
		// Add edges //
		for (int i=0;i<12;i++) {
			root.getChildren().add(this.edges[i]);
		}
		
		return root;
	}
	
	// -- Functions -- //
	private void createNodes() {	// Sets up the nodes
		// Instantiate the nodes //
		for (int i=0;i<8;i++) {
			this.nodes[i] = new Sphere(this.radius);
		}
		
		// Position the nodes //
			// X Property //
		for (int i=0;i<8;i++) {
			switch (i%2) {
				case 0:
					this.nodes[i].translateXProperty().set(-this.length/2);
					break;
				case 1:
					this.nodes[i].translateXProperty().set(this.length/2);
					break;
				default:
					break;
			}
		}
		
			// Y Property //
		for (int i=0;i<4;i++) {
			for (int j=0;j<2;j++) {
				switch (i%2) {
					case 0:
						this.nodes[2*i + j].translateYProperty().set(this.length/2);
						break;
					case 1:
						this.nodes[2*i + j].translateYProperty().set(-this.length/2);
						break;
					default:
						break;
				}
			}
		}
		
			// Z Property //
		for (int i=0;i<8;i++) {
			switch (i) {
				case 0:
				case 1:
				case 2:
				case 3:
					this.nodes[i].translateZProperty().set(-this.length/2);
					break;
				case 4:
				case 5:
				case 6:
				case 7:
					this.nodes[i].translateZProperty().set(this.length/2);
					break;
				default:
					break;
			}
		}
	}
	
	private void createEdges() {	// Sets up the edges
		// Instantiate Cylinder Objects //
		for (int i=0;i<12;i++) {
			this.edges[i] = new Cylinder(this.radius, this.length);
		}
		
		// Edges || to X-Axis //
		Transform rotateZ = new Rotate(90, Rotate.Z_AXIS);
		for (int i=0;i<4;i++) {
			this.edges[i].getTransforms().add(rotateZ);
		}
		
		this.edges[0].translateXProperty().set(0);
		this.edges[0].translateYProperty().set(this.length/2);
		this.edges[0].translateZProperty().set(-this.length/2);
		
		this.edges[1].translateXProperty().set(0);
		this.edges[1].translateYProperty().set(-this.length/2);
		this.edges[1].translateZProperty().set(-this.length/2);
		
		PhongMaterial redMat = new PhongMaterial();
		redMat.setDiffuseColor(Color.RED);
		this.edges[1].setMaterial(redMat);
		
		this.edges[2].translateXProperty().set(0);
		this.edges[2].translateYProperty().set(this.length/2);
		this.edges[2].translateZProperty().set(this.length/2);
		
		this.edges[3].translateXProperty().set(0);
		this.edges[3].translateYProperty().set(-this.length/2);
		this.edges[3].translateZProperty().set(this.length/2);
		
		// Edges || to Y-Axis //
		this.edges[4].translateXProperty().set(-this.length/2);
		this.edges[4].translateYProperty().set(0);
		this.edges[4].translateZProperty().set(-this.length/2);
		
		PhongMaterial greenMat = new PhongMaterial();
		greenMat.setDiffuseColor(Color.GREEN);
		this.edges[4].setMaterial(greenMat);
		
		this.edges[5].translateXProperty().set(this.length/2);
		this.edges[5].translateYProperty().set(0);
		this.edges[5].translateZProperty().set(-this.length/2);
		
		this.edges[6].translateXProperty().set(-this.length/2);
		this.edges[6].translateYProperty().set(0);
		this.edges[6].translateZProperty().set(this.length/2);
		
		this.edges[7].translateXProperty().set(this.length/2);
		this.edges[7].translateYProperty().set(0);
		this.edges[7].translateZProperty().set(this.length/2);
		
		// Edges || to Z-Axis //
		Transform rotateX = new Rotate(90, Rotate.X_AXIS);
		for (int i=8;i<12;i++) {
			this.edges[i].getTransforms().add(rotateX);
		}
		
		this.edges[8].translateXProperty().set(-this.length/2);
		this.edges[8].translateYProperty().set(this.length/2);
		this.edges[8].translateZProperty().set(0);
		
		this.edges[9].translateXProperty().set(this.length/2);
		this.edges[9].translateYProperty().set(this.length/2);
		this.edges[9].translateZProperty().set(0);
		
		this.edges[10].translateXProperty().set(-this.length/2);
		this.edges[10].translateYProperty().set(-this.length/2);
		this.edges[10].translateZProperty().set(0);
		
		PhongMaterial blueMat = new PhongMaterial();
		blueMat.setDiffuseColor(Color.BLUE);
		this.edges[10].setMaterial(blueMat);
		
		this.edges[11].translateXProperty().set(this.length/2);
		this.edges[11].translateYProperty().set(-this.length/2);
		this.edges[11].translateZProperty().set(0);
	}
}
