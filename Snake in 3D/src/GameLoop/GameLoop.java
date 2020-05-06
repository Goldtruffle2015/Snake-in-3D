package GameLoop;

public class GameLoop {
	// -- Attributes -- //
	private long lastTime;	// Tracks the last recorded time
	private double amountOfTicks;	// FPS
	private double ns;	// Nanoseconds per tick to get specified FPS (60)
	private double delta;	// Keeps track of the number of ticks passed
	
	// -- Constructor -- //
	public GameLoop(int fps) {
		this.amountOfTicks = fps;
		this.ns = 1000000000 / this.amountOfTicks;
		this.delta = 0;
	}
	
	// -- Setter -- //
	public void setLastTime(long now) {
		this.lastTime = now;
	}
	
	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	// -- Getter -- //
	public long getLastTime() {
		return this.lastTime;
	}
	
	public double getDelta() {
		return this.delta;
	}
	
	// -- Methods -- //
	public void calcDelta(long now) {
		this.delta += (now - this.lastTime) / this.ns;
		this.lastTime = now;
	}
}
