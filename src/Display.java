import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Display extends JFrame {
	private int height = 800;
	private int width = 800;
	private Container contents;
	//private ImageIcon covered = new ImageIcon("covered.jpg");
	//private ImageIcon covered = new ImageIcon("uncovered.jpg");
	//private ImageIcon covered = new ImageIcon("marked.jpg");
	//private ImageIcon covered = new ImageIcon("mined.jpg");
	
	private JButton[][] squares = new JButton[10][10];
	
	public Display() {
		contents = getContentPane();
		contents.setLayout(new GridLayout(10,10));
		ButtonHandler buttonHandler = new ButtonHandler();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setBackground(Color.DARK_GRAY);
				contents.add(squares[i][j]);
				squares[i][j].addActionListener(buttonHandler);
			}
		}
		this.setSize(width,height);
		this.setTitle("Minesweeper");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
/*
	private boolean isValidMove(int i, int j) {
		int rowDelta = Math.abs(i - row);
		int colDelta = Math.abs(j - col);
		
		if((rowDelta == 1) && (colDelta == 2)) {
			return true;
		}
		if((colDelta == 1) && (rowDelta ==2)) {
			return true;
		}
		return false;
	}
*/	
	
	public void clickProcess() {
		
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			for(int i =0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(source ==squares[i][j]) {
						squares[i][j].setBackground(Color.white);
							clickProcess();
						return;
					}
				}
			}
		}
	}	
}