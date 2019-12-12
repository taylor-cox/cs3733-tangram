package sp.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ExitController extends AbstractAction {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
