package sp.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sp.boundry.DoubleSidedApp;
import sp.entity.Model;

public class FlipController extends MouseAdapter {
	Model model;
	DoubleSidedApp app;
	
	public FlipController(DoubleSidedApp app, Model m) {
		this.app = app;
		this.model = m;
	}
	
	public void mousePressed(MouseEvent me) {
		if(model.canPlay()) {
			Point p = me.getPoint();
			int col = (int) (p.getX() / 100), row = (int) (p.getY() / 100);

			if(model.clickTile(row, col)) {
				app.updateMoveCounter(model.getNumMoves());
			}

			app.redraw();
		}
	}
}
