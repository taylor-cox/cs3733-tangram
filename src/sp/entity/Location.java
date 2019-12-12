package sp.entity;

public class Location {
	public int row;
	public int col;
	
	public Location (int r, int c) {
		row = r;
		col = c;
	}
	
	public int getIndex() {
		return row*3 + col;
	}
	
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
	public boolean equals(Location l) {
		return this.getIndex() == l.getIndex();
	}
}
