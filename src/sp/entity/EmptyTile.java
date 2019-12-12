package sp.entity;

public class EmptyTile extends Tile {
	public EmptyTile(Location l) {
		super(0, 0, false);
		this.location = l;
	}
}
