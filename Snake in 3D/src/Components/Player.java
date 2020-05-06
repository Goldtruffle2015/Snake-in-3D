package Components;

import java.util.ArrayList;

import Movement.SmartGroup;

public class Player {	// Contains bodies
	// -- Attributes -- //
	private ArrayList<Body> bodies;	// Contains each body
	
	// -- Constructor -- //
	public Player() {
		this.bodies = new ArrayList<Body>();
	}
	
	// -- Methods -- //
	public SmartGroup addWith(SmartGroup group) {
		for (int i=0;i<this.bodies.size();i++) {
			group = this.bodies.get(i).addWith(group);
		}
		return group;
	}
	
	public void addBody(int x, int y, int z) {
		this.bodies.add(new Body(x, y, z));
	}
}
