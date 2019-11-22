import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class Display extends JFrame {
	private int width = 500;
	private int height = 500;
	private int cols = 10;
	private int rows = 10;
	protected int number_of_mines=10;

	private JPanel mainPanel;
	private JPanel menuPanel;
	private JPanel gamePanel;
	private JButton[][] field = new JButton[cols][rows];
	
	Game g;
	
	public Display() {
		g = new Game(rows,cols,number_of_mines);
		g.createGame();
		this.setSize(width,height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Minesweeper");
		this.setLayout(new BorderLayout());

		mainPanel = new JPanel();

		gamePanel = new JPanel();
		GridLayout gamePanel_layout = new GridLayout(cols,rows);
		gamePanel.setLayout(gamePanel_layout);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				field[i][j] = new JButton();
				field[i][j].setMargin(new Insets(0, 0, 0, 0));
				field[i][j].setBackground(Color.DARK_GRAY);
				gamePanel.add(field[i][j]);
				field[i][j].addMouseListener(new ClickListener());
			}
		}
		mainPanel.add(gamePanel);

		menuPanel = new JPanel();
		this.add(menuPanel, BorderLayout.NORTH);
		this.add(gamePanel, BorderLayout.CENTER);
		
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
								if(g.table.table[i][j].isMine()){
									g.table.revealAll();
								}
								else {
									if(g.table.table[i][j].getValue() == 0){
										g.table.discover(i,j);
									}
									else {
										g.table.table[i][j].uncover();
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if(g.table.table[i][j].isCovered()){
							if(g.table.table[i][j].isMarked()) {
								field[i][j].setBackground(Color.GREEN);
							}
							else {
								field[i][j].setBackground(Color.DARK_GRAY);
							}
						}
						else {
							if (g.table.table[i][j].isMine()) {
								field[i][j].setBackground(Color.RED);
							} else {
								field[i][j].setBackground(Color.WHITE);
								if (g.table.table[i][j].getValue() > 0)
									field[i][j].setText(paramString().valueOf(g.table.table[i][j].getValue()));
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
								g.table.table[i][j].mark();
								System.out.println(g.table.table[i][j].isMarked());
							}
						}
					}
				}
				for(int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if(g.table.table[i][j].isCovered()){
							if(g.table.table[i][j].isMarked()) {
								field[i][j].setBackground(Color.GREEN);
							}
							else {
								field[i][j].setBackground(Color.DARK_GRAY);
							}
						}
						else {
							if (g.table.table[i][j].isMine()) {
								field[i][j].setBackground(Color.RED);
							} else {
								field[i][j].setBackground(Color.WHITE);
								if (g.table.table[i][j].getValue() > 0)
									field[i][j].setText(paramString().valueOf(g.table.table[i][j].getValue()));
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

