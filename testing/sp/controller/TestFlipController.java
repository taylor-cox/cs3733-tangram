package sp.controller;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import org.junit.Test;

import sp.boundry.DoubleSidedApp;
import sp.entity.EmptyTile;
import sp.entity.Model;

public class TestFlipController {
	@Test
	public void testFlipController() throws Exception {
		Model m = new Model();
		DoubleSidedApp dsa = new DoubleSidedApp(m);
		FlipController fc = new FlipController(dsa, m);
		dsa.setVisible(true);
		fc.mousePressed(new MouseEvent(dsa.getPuzzleView(), MouseEvent.MOUSE_CLICKED, 1, MouseEvent.BUTTON1_MASK, 150, 250, 1, false));
		assertTrue(m.getTiles().get(7) instanceof EmptyTile);
	}
}
