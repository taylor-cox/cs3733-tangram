package sp.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sp.entity.Location;

public class TestLocation {
	@Test
	public void testLocation() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Location l = new Location(i, j);
				assertEquals(l.row, i);
				assertEquals(l.col, j);
				assertEquals(l.toString(), "(" + i + ", " + j + ")");
				assertEquals(l.getIndex(), i*3 + j);
			}
		}
	}
}
