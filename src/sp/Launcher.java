package sp;

import sp.boundry.DoubleSidedApp;
import sp.entity.Model;

public class Launcher {
	public static void main(String[] args) {
		Model m = new Model();
		DoubleSidedApp dsa = new DoubleSidedApp(m);
		
		dsa.setVisible(true);
	}
}
