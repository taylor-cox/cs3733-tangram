package sp.boundry;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import sp.entity.EmptyTile;
import sp.entity.Location;
import sp.entity.Model;
import sp.entity.Tile;

@SuppressWarnings("serial")
public class PuzzleView extends JPanel {
	Model model;
	
	public PuzzleView(Model model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
		
		for(Tile t : model.getTiles())
			paintTile(t, g);
		
	}
	
	private void paintTile(Tile t, Graphics g) {
		int dig = t.visibleDigit();
		Location loc = t.getLocation();
		
		int x = 100*loc.col;
		int y = 100*loc.row;
		
		Color tileBackgroundColor, tileTextColor;
		if(t.isFlipped()) {
			tileBackgroundColor = Color.black;
			tileTextColor = Color.white;
		} else {
			tileBackgroundColor = Color.gray;
			tileTextColor = Color.black;
		}
		
		if(t instanceof EmptyTile) {
			tileBackgroundColor = Color.orange;
			g.setColor(tileBackgroundColor);
			g.fillRect(x, y, 100, 100);
		} else {
			g.setColor(tileBackgroundColor);
			g.fillRect(x, y, 100, 100);
			g.setColor(tileTextColor);
			g.drawString("" + dig, x + 40, y + 65);	
		}
		
		g.setColor(Color.white);
		g.drawRect(x, y, 100, 100);
		g.setColor(new Color(0x123456));
		g.drawRect(x + 1, y + 1, 98, 98);
		if(model.gameWon()) {
			g.setColor(Color.green);
			g.drawString("You won!", 50, 150);	
		} else if(model.gameLost()) {
			g.setColor(Color.red);
			g.drawString("You lost!", 50, 150);	
		}
	}
}
