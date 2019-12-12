package sp.boundry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sp.controller.ExitController;
import sp.controller.FlipController;
import sp.controller.ResetController;
import sp.entity.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

@SuppressWarnings("serial")
public class DoubleSidedApp extends JFrame {

	private JPanel contentPane;
	PuzzleView panel;
	Model model;
	JLabel numberMovesLabel;
	
	public DoubleSidedApp(Model model) {
		this.model = model;
		setResizable(false);
		setTitle("Double Slided App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new PuzzleView(model);
		panel.addMouseListener(new FlipController(this, model));
		
		JMenuBar mb = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		menu1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        mb.add(menu1);
        JMenu menu2 = new JMenu("Puzzle");
        menu2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        mb.add(menu2);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("Tahoma", Font.PLAIN, 24));
        exit.addActionListener(new ExitController());
        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(new ResetController(this, model));
        reset.setFont(new Font("Tahoma", Font.PLAIN, 24));
        menu1.add(exit);
        menu2.add(reset);
        this.setJMenuBar(mb);
		
		JLabel lblNumberOfMoves = new JLabel("Number of Moves: ");
		lblNumberOfMoves.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		this.numberMovesLabel = new JLabel("0"); 
		numberMovesLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addGap(86))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNumberOfMoves)
							.addGap(18)
							.addComponent(numberMovesLabel)))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNumberOfMoves)
							.addComponent(numberMovesLabel))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
					.addGap(124)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void redraw() {
		panel.repaint();
	}
	
	public void updateMoveCounter(int moves) {
		this.numberMovesLabel.setText(moves + "");
	}
	
	public PuzzleView getPuzzleView() {
		return this.panel;
	}
}
