package sp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.util.Random;

import org.junit.Test;

import sp.boundry.DoubleSidedApp;
import sp.entity.Model;

public class TestResetController {
	@Test
	public void testResetController() {
		Model m = new Model();
		DoubleSidedApp dsa = new DoubleSidedApp(m);
		ResetController rc = new ResetController(dsa, m);
		Random r = new Random();
		
		// Mix up board
		for(int i = 0; i < 1000; i++) {
			int x = r.nextInt(3), y = r.nextInt(3);
			m.clickTile(x, y);
		}
		assertFalse(m.inOriginalConfiguration());
		
		rc.actionPerformed(new ActionEvent(dsa.getPuzzleView(), ActionEvent.ACTION_PERFORMED, ""));
		
		assertTrue(m.inOriginalConfiguration());
	}
}
