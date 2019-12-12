package sp.entity;

public class Tile {
	 final int up;
	 final int down;
	 boolean flipped;
	 Location location;

	public Tile (int up, int down, boolean flipped) {
		this.up = up;
		this.down = down;
		this.flipped = flipped;
	}
	
	public void setLocation(Location loc) {
		location = loc;
	}
	
	public Location getLocation() { return location; }
	
	public int visibleDigit() {
		return flipped ? down : up;
	}
	
	public boolean isFlipped() {
		return flipped;
	}
	
	public void flip() {
		flipped = !flipped;
	}
}
