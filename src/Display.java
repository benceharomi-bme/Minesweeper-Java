import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Display extends JFrame {
	private int width = 400;
	private int height = 400;
	private int cols = 10;
	private int rows = 10;

	private JPanel panel;
	private JButton[][] field = new JButton[cols][rows];
	
	Game g;
	
	public Display() {
		g = new Game(10,10,25);
		g.createGame();
		this.setSize(width,height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Minesweeper");
		
		GridLayout l = new GridLayout();
		l.setRows(1);
		l.setColumns(1);
		setLayout(l);
		this.setLayout(l);
		
		
		panel = new JPanel();
		GridLayout panel_layout = new GridLayout(cols,rows);
		panel.setLayout(panel_layout);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				field[i][j] = new JButton();
				field[i][j].setMargin(new Insets(0, 0, 0, 0));
				field[i][j].setBackground(Color.DARK_GRAY);
				panel.add(field[i][j]);
				field[i][j].addMouseListener(new ClickListener());
			}
		}
		this.add(panel);
		
	}
	
	public class ClickListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {

			e.getButton();
			if(e.getButton() == MouseEvent.BUTTON1) {
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						if(e.getSource() == field[i][j]) {
							if(g.table.table[i][j].isCovered() && !g.table.table[i][j].isMarked()) {
								if(g.table.table[i][j].isMine()) {
									field[i][j].setBackground(Color.RED);
								}
								else {
									field[i][j].setBackground(Color.WHITE);
									field[i][j].setText(paramString().valueOf(g.table.table[i][j].getValue()));
								}
								g.table.table[i][j].uncover();

							}
						}
					}
				}
			}
			
			else if(e.getButton() == MouseEvent.BUTTON3) {
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						if(e.getSource() == field[i][j]) {
							if(g.table.table[i][j].isCovered()) {
								if(g.table.table[i][j].isMarked()) {
									field[i][j].setBackground(Color.DARK_GRAY);
								}
								else {
									field[i][j].setBackground(Color.GREEN);
								}
								g.table.table[i][j].mark();
								System.out.println(g.table.table[i][j].isMarked());
							}
						}
					}
				}
			}
			g.draw();
			System.out.println();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	}
}

