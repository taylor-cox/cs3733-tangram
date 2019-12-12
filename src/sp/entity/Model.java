package sp.entity;

import java.util.ArrayList;

public class Model {

	ArrayList<Tile> tiles;
	int moves;
	boolean playable;
	final String[][] originalConfiguration = {{"1u", "4u", "3d"},
					   						  {"2u", "1u", "2d"},
					   						  {"3u", "4u", "em"}};;

	public Model () {
		tiles = new ArrayList<Tile>();
		setupTiles();
		this.playable = true;
	}

	private void setupTiles() {
		// String list of the initial setup, 
		// in form "[whatever number is up][if the tile is up or down]", and "em" for empty

		// For testing purposes, gameWon
		//		String[][] initialSetup = {{"1u", "2u", "3u"},
		//				                   {"4d", "3u", "4u"},
		//				                   {"3d", "em", "1d"}};

		// For testing purposes, gameLost
//				String[][] initialSetup = {{"1u", "1u", "1u"},
//										   {"4d", "3u", "4u"},
//										   {"3d", "em", "4d"}};

		int r = 0, c = 0;

		for(String[] row : this.originalConfiguration) {
			for(String tile : row) {
				if(tile.equals("em")) {
					tiles.add(new EmptyTile(new Location(r, c++)));
					continue;
				}
				int num = Character.getNumericValue(tile.charAt(0));
				boolean flipped = tile.charAt(1) == 'd';
				int numUp = flipped ? 5 - num : num;
				Tile t = new Tile(numUp, 5 - numUp, flipped);
				t.setLocation(new Location(r, c++));
				tiles.add(t);
			}
			r++;
			c = 0;
		}
	}


	public ArrayList<Tile> getTiles() { 
		return tiles; 
	}

	public Location borderingEmpty(Tile t) {
		Location l = t.getLocation();
		int row = l.row, col = l.col;

		// Check above
		if(--row >= 0 && tiles.get(row*3 + col) instanceof EmptyTile)
			return tiles.get(row*3 + col).getLocation();
		else row++;

		// Check left
		if(--col >= 0 && tiles.get(row*3 + col) instanceof EmptyTile)
			return tiles.get(row*3 + col).getLocation();
		else col++;

		// Check right
		if(++row < 3 && tiles.get(row*3 + col) instanceof EmptyTile)
			return tiles.get(row*3 + col).getLocation();
		else row--;

		// Check down
		if(++col < 3 && tiles.get(row*3 + col) instanceof EmptyTile)
			return tiles.get(row*3 + col).getLocation();
		return null;
	}
	
	public boolean clickTile(int row, int col) {
		Tile t = tiles.get(row*3 + col);
		Location toSwap = this.borderingEmpty(t);
		if(toSwap != null) {
			this.swap(t.getLocation(), toSwap);
			return true;
		} else return false;
	}

	public void swap(Location to, Location from) {
		int indexTo = to.getIndex(), indexFrom = from.getIndex();
		Tile tTo = tiles.get(indexTo);
		Tile tFrom = tiles.get(indexFrom);
		tiles.set(indexTo, tFrom);
		tiles.set(indexFrom, tTo);
		tTo.setLocation(from);
		tFrom.setLocation(to);

		tTo.flip();
		tFrom.flip();

		moves++;
	}

	public int getNumMoves() {
		return this.moves;
	}

	public void reset() {
		tiles.clear();
		this.setupTiles();
		this.moves = 0;
		this.playable = true;
	}

	public boolean gameWon() {
		String[][] winningConfiguration = {{"1u", "2u", "3u"},
										   {"4d", "em", "4u"},
										   {"3d", "2d", "1d"}};

		int counter = 0;
		for(String[] row : winningConfiguration) {
			for(String stringTile : row) {
				Tile workingTile = tiles.get(counter++);

				if(stringTile.equals("em") && workingTile instanceof EmptyTile)
					continue;

				int num = Character.getNumericValue(stringTile.charAt(0));
				boolean flipped = stringTile.charAt(1) == 'd';

				if(!(flipped == workingTile.isFlipped()) || !(workingTile.visibleDigit() == num))
					return false;
			}
		}
		this.playable = false;
		return true;
	}

	public boolean gameLost() {
		int[] outputs = new int[5];
		for(Tile t : tiles) {
			outputs[t.visibleDigit()]++;
		}
		for(int i : outputs) {
			if(i >= 4) { 
				this.playable = false;
				return true;
			}
		}
		return false;
	}
	
	public boolean canPlay() {
		return this.playable;
	}
	
	public void setTiles(ArrayList<Tile> t) {
		this.tiles = t;
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < this.tiles.size(); i++) {
			Tile working = this.tiles.get(i);
			char upDown = working.isFlipped() ? 'd' : 'u';
			if(working.visibleDigit() != 0) output += "" + working.visibleDigit() + upDown + " ";
			else output += "em ";
			if((i + 1) % 3 == 0) output += "\n";
		}	
		return output;
	}
	
	public boolean inOriginalConfiguration() {
		for(int i = 0; i < this.tiles.size(); i++) {
			String toCheck = "";
			Tile working = this.tiles.get(i);
			char upDown = working.isFlipped() ? 'd' : 'u';
			if(working.visibleDigit() != 0) toCheck += "" + working.visibleDigit() + upDown;
			else toCheck += "em";
			if(!this.originalConfiguration[(int) Math.floor(i / 3)][i % 3].equals(toCheck)) return false;
		}
		return true;
	}
}
