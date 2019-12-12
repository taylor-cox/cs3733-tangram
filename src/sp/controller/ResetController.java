package sp.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import sp.boundry.DoubleSidedApp;
import sp.entity.Model;

@SuppressWarnings("serial")
public class ResetController extends AbstractAction {
	Model model;
	DoubleSidedApp app;
	
	public ResetController(DoubleSidedApp app, Model m) {
		this.app = app;
		this.model = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.reset();
		app.redraw();
		app.updateMoveCounter(model.getNumMoves());
	}
}
