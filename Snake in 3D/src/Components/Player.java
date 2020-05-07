package Components;

import java.util.ArrayList;

import Movement.SmartGroup;

public class Player {	// Contains bodies
	// -- Attributes -- //
	private ArrayList<Body> bodies;	// Contains each body
	private int spd;	// Defines the number of units to move
	private int xVel, yVel, zVel;	// Defines the direction
	private int x, y, z;	// Defines the position of the head
	
	// -- Constructor -- //
	public Player() {
		this.bodies = new ArrayList<Body>();
		this.spd = 20;
		this.xVel = 1;
		this.yVel = 0;
		this.zVel = 0;
	}
	
	// -- Setter -- //
	public void setXVel(int xVel) {
		this.xVel = xVel;
	}
	
	public void setYVel(int yVel) {
		this.yVel = yVel;
	}
	
	public void setZVel(int zVel) {
		this.zVel = zVel;
	}
	
	// -- Getter -- //
	public int getXVel() {
		return this.xVel;
	}
	
	public int getYVel() {
		return this.yVel;
	}
	
	public int getZVel() {
		return this.zVel;
	}
	
	public ArrayList<Body> getBody() {
		return this.bodies;
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
	
	public void Move() {
		for (int i=this.bodies.size()-1;i>=0;i--) {
			if (i == 0) {
				this.x += this.xVel * this.spd;
				this.y += this.yVel * this.spd;
				this.z += this.zVel * this.spd;
				
				this.bodies.get(i).setX(this.x);
				this.bodies.get(i).setY(this.y);
				this.bodies.get(i).setZ(this.z);
			} else {
				this.bodies.get(i).setX(this.bodies.get(i - 1).getX());
				this.bodies.get(i).setY(this.bodies.get(i - 1).getY());
				this.bodies.get(i).setZ(this.bodies.get(i - 1).getZ());
			}
			this.bodies.get(i).setPos();
		}
	}
}
