package sp.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import sp.entity.EmptyTile;
import sp.entity.Location;
import sp.entity.Model;
import sp.entity.Tile;

public class TestModel {

	@Test
	public void testContructor() {
		Model m = new Model();
		assertTrue(m.canPlay());

		String[][] initialSetup = {{"1u", "4u", "3d"},
				{"2u", "1u", "2d"},
				{"3u", "4u", "em"}};

		ArrayList<Tile> tiles = constructTileArray(initialSetup);

		for(int i = 0; i < tiles.size(); i++) {
			assertEquals(tiles.get(i).visibleDigit(), m.getTiles().get(i).visibleDigit());
			assertEquals(tiles.get(i).isFlipped(), m.getTiles().get(i).isFlipped());
			assertTrue(tiles.get(i).getLocation().equals(m.getTiles().get(i).getLocation()));
		}

	}

	@Test
	public void testBorderingEmpty() {
		Model m = new Model();

		String[][] initialSetup = {{"em", "em", "em"},
				{"em", "em", "em"},
				{"em", "em", "em"}};

		m.setTiles(constructTileArray(initialSetup));

		for(int i = 0; i < 9; i++) 
			assertTrue((m.borderingEmpty(m.getTiles().get(i))) instanceof Location);

		initialSetup[1][0] = "1u";
		m.setTiles(constructTileArray(initialSetup));

		for(int i = 0; i < 9; i++) 
			assertTrue((m.borderingEmpty(m.getTiles().get(i))) instanceof Location);


		String[][] n = {{"1u", "1u", "1u"},
				{"1u", "1u", "1u"},
				{"1u", "1u", "1u"}};

		m.setTiles(constructTileArray(n));
		for(int i = 0; i < 9; i++) 
			assertNull(((m.borderingEmpty(m.getTiles().get(i)))));

		String[][] middle = {{"1u", "1u", "1u"},
				{"1u", "em", "1u"},
				{"1u", "1u", "1u"}};

		m.setTiles(constructTileArray(middle));
		for(int i = 0; i < 9; i++) 
			if(i == 1 || i == 3 || i == 5 || i == 7)
				assertNotNull(m.borderingEmpty(m.getTiles().get(i)));
			else
				assertNull(m.borderingEmpty(m.getTiles().get(i)));
	}

	@Test
	public void testClickTile() {
		// TODO
		Model m = new Model();
		m.clickTile(2, 1);
		assertEquals(m.getNumMoves(), 1);
		m.clickTile(0, 0);
		assertEquals(m.getNumMoves(), 1);
	}

	@Test
	public void testCanPlay() {
		Model m = new Model();
		String[][] losingConfiguation = {{"1u", "1u", "1u"},
				{"4d", "em", "4u"},
				{"3d", "2d", "1d"}};

		assertEquals(m.canPlay(), true);
		m.setTiles(constructTileArray(losingConfiguation));
		assertEquals(m.gameLost(), true);
		assertEquals(m.canPlay(), false);
	}

	@Test
	public void testReset() {
		Model m = new Model();
		String[][] initialSetup = {{"1u", "4u", "3d"},
								   {"2u", "1u", "2d"},
								   {"3u", "4u", "em"}};

		Random r = new Random();
		
		// Mix up board
		for(int i = 0; i < 1000; i++) {
			int x = r.nextInt(3), y = r.nextInt(3);
			m.clickTile(x, y);
		}

		ArrayList<Tile> tiles = constructTileArray(initialSetup);
		m.reset();
		for(int i = 0; i < tiles.size(); i++) {
			assertEquals(tiles.get(i).visibleDigit(), m.getTiles().get(i).visibleDigit());
			assertEquals(tiles.get(i).isFlipped(), m.getTiles().get(i).isFlipped());
			assertTrue(tiles.get(i).getLocation().equals(m.getTiles().get(i).getLocation()));
		}
	}

	@Test
	public void testGameWon() {
		Model m = new Model();

		String[][] winningConfiguration = {{"1u", "2u", "3u"},
				{"4d", "em", "4u"},
				{"3d", "2d", "1d"}};

		m.setTiles(constructTileArray(winningConfiguration));
		assertTrue(m.gameWon());

		String[][] notWinningConfiguration = {{"2u", "2u", "3u"},
				{"4d", "em", "4u"},
				{"3d", "2d", "1d"}};

		m.setTiles(constructTileArray(notWinningConfiguration));

		assertFalse(m.gameWon());
	}

	@Test
	public void testGameLost() {
		Model m = new Model();

		String[][] losingConfiguation = {{"1u", "1u", "1u"},
				{"4d", "em", "4u"},
				{"3d", "2d", "1d"}};

		m.setTiles(constructTileArray(losingConfiguation));
		assertTrue(m.gameLost());

		String[][] notLosingConfiguration = {{"2u", "2u", "3u"},
				{"4d", "em", "4u"},
				{"3d", "2d", "1d"}};

		m.setTiles(constructTileArray(notLosingConfiguration));

		assertFalse(m.gameLost());
	}
	
	@Test
	public void testInOriginalConfiguration() {
		Model m = new Model();
		assertTrue(m.inOriginalConfiguration());
		Random r = new Random();
		// Mix up board
		for(int i = 0; i < 1000; i++) {
			int x = r.nextInt(3), y = r.nextInt(3);
			m.clickTile(x, y);
		}
		assertFalse(m.inOriginalConfiguration());
		m.reset();
		assertTrue(m.inOriginalConfiguration());
	}
	
	@Test
	public void testToString() {
		String initialSetup = "1u 4u 3d \n2u 1u 2d \n3u 4u em \n";
		Model m = new Model();
		assertEquals(0, initialSetup.compareTo(m.toString()));
	}

	private ArrayList<Tile> constructTileArray(String[][] setup) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int r = 0, c = 0;

		for(String[] row : setup) {
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

		return tiles;
	}
}
