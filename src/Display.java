import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** This is the main frame (The display) where everything happens
 * @author Bence
 *
 */
@SuppressWarnings("serial")
public class Display extends JFrame {
	
	private int cols = 10;
	private int rows = 10;
	private int number_of_mines=10;
	private int mines_left;
	private boolean win = false;
	private  boolean gameOver = false;

	private JLabel mines;
	private JButton[][] field = new JButton[cols][rows];

	private Game game;

	public Display() {
		//creating the game with the given rows, cols, mines
		game = new Game(rows,cols,number_of_mines);
		game.createGame();

		//frame settings
		int height = 440;
		int width = 400;
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Minesweeper");
		this.setLayout(new BorderLayout());

		//the main panel of the frame
		JPanel mainPanel = new JPanel();

		//the panel, where the player plays (this is the board)
		JPanel gamePanel = new JPanel();
		//gridlayout is the convenient for a minesweeper table
		GridLayout gamePanel_layout = new GridLayout(cols,rows);
		gamePanel.setLayout(gamePanel_layout);
		//adding buttons to all of the grids, setting them up
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				field[i][j] = new JButton();
				field[i][j].setMargin(new Insets(0, 0, 0, 0));
				field[i][j].setBackground(Color.DARK_GRAY);
				gamePanel.add(field[i][j]);
				field[i][j].addMouseListener(new ClickListener());
			}
		}
		//adding the gamepanel to the main panel
		mainPanel.add(gamePanel);

		//menupanel, where the player could choose level
		// and could save or load the game
		// adding the buttons to the panel
		JPanel menuPanel = new JPanel();
		JButton easyButton = new JButton("Easy");
		easyButton.addActionListener(new ButtonListener());
		menuPanel.add(easyButton);

		JButton normalButton = new JButton("Normal");
		normalButton.addActionListener(new ButtonListener());
		menuPanel.add(normalButton);

		JButton hardButton = new JButton("Hard");
		hardButton.addActionListener(new ButtonListener());
		menuPanel.add(hardButton);

		JButton saveb = new JButton("Save");
		saveb.addActionListener(new ButtonListener());
		menuPanel.add(saveb);

		JButton loadb = new JButton("Load");
		loadb.addActionListener(new ButtonListener());
		menuPanel.add(loadb);

		// adding the mine counter
		// this shows the number of mines
		mines = new JLabel();
		mines_left = number_of_mines;
		mines.setText(number_of_mines + "/" + mines_left);
		menuPanel.add(mines);

		// adding the panels to the frame
		mainPanel.add(menuPanel);
		this.add(menuPanel, BorderLayout.NORTH);
		this.add(gamePanel, BorderLayout.CENTER);

		// locating the frame to the center of the display
		this.setLocationRelativeTo(null);
	}

	/** This is the frame which appears when the game is over
	 * @author Bence
	 *
	 */
	public class GameOverFrame extends JFrame {
		
		JLabel label;
		
		public GameOverFrame(){
			this.setLayout(new BorderLayout());
			this.setSize(300,100);

			//The text labels which tells the outcome of the game (win/lose)
			JPanel buttonPanel = new JPanel(new FlowLayout());
			if(win){
				label = new JLabel("You win");
			}
			else{
				label = new JLabel("You lost");
			}
			this.add(label,BorderLayout.PAGE_START);

			//Adding the buttons with different options
			//Restarts the game
			JButton tryAgainButton = new JButton("Try again");
			tryAgainButton.addActionListener(new GameOverButtonListener());
			buttonPanel.add(tryAgainButton);
			//Opens the leaderboard
			JButton leaderBoarButton = new JButton("Leaderboard");
			leaderBoarButton.addActionListener(new GameOverButtonListener());
			buttonPanel.add(leaderBoarButton);
			//Exits
			JButton exitButton = new JButton("Exit");
			exitButton.addActionListener(new GameOverButtonListener());
			buttonPanel.add(exitButton);
			//Adding the buttonpanel to the center of the frame
			this.add(buttonPanel,BorderLayout.CENTER);
			setLocationRelativeTo(null);
		}

		/** The frame of the Leaderboard
		 * @author Bence
		 *
		 */
		public class LeaderBoardFrame extends JFrame{
			
			private JFrame frame;
			private JTable table;
			private LeaderBoard lb;
			private JTextField name;
			private DefaultTableModel tableModel;
			private JFrame nameFrame;
			private boolean noFile = false;

			public LeaderBoardFrame() {
				
				frame = new JFrame();
				frame.setTitle("Leaderboard");
				lb = new LeaderBoard();
				
				//Setting the name of the columns
				String[] columnNames = { "Nickame", "Time (seconds)" };
				tableModel = new DefaultTableModel(columnNames, 0);

				//Initializing the JTable
				table = new JTable(tableModel);
				table.setBounds(30, 40, 200, 300);
				//Initializing the pane
				JScrollPane sp = new JScrollPane(table);
				frame.add(sp);

				JLabel lb_notfound = new JLabel();
				try {
					//loading the leaderboard from file
					lb.loadFromFile();
				} catch (IOException e) {
					lb_notfound.setText("Leaderboard save not found");
					noFile = true;
				}

				if(win) {
					//frame where the player enters the nickname
					nameFrame = new JFrame();
					JLabel question = new JLabel("Enter your nickname:");
					nameFrame.add(question);
					name = new JTextField(10);
					JPanel playerPanel = new JPanel();
					playerPanel.add(name);
					JButton nameButton = new JButton("OK");
					nameButton.addActionListener(new OkButtonListener());
					playerPanel.add(nameButton);
					nameFrame.add(playerPanel);

					nameFrame.setSize(200, 100);
					nameFrame.setVisible(true);
					nameFrame.setLocationRelativeTo(null);
				}
				else if(noFile) {
					frame.add(lb_notfound);
					frame.setVisible(true);
				}
				else if (!noFile){
					for (int i = 0;i < lb.getSize(); i++) {
						Object[] objs = {lb.getName(i), lb.getTime(i)};
						tableModel.addRow(objs);
					}
					frame.setVisible(true);
				}
				frame.setSize(500, 200);
				frame.setLocationRelativeTo(null);
			}

			public class OkButtonListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {

					lb.addPlayer(name.getText(),game.getTime());
					try {
						//saving the list to file
						lb.saveToFile();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					noFile = false;
					//reading the list to the table
					for (int i = 0;i < lb.getSize(); i++) {
						Object[] objs = {lb.getName(i), lb.getTime(i)};
						tableModel.addRow(objs);
					}
					nameFrame.dispose();
					frame.setVisible(true);
				}
			}
		}

		public class GameOverButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//creating a new game
                if(e.getActionCommand().equals("Try again")){
                    win = false;
                    gameOver = false;
                    game = new Game(rows, cols, number_of_mines);
                    game.createGame();
                    mines_left = number_of_mines;
                    refresh();
                    dispose();
                }
                //opening the leaderboard frame
                else if(e.getActionCommand().equals("Leaderboard")){
					LeaderBoardFrame lbf = new LeaderBoardFrame();
					lbf.setResizable(false);
				}
                //exiting
                else if(e.getActionCommand().equals("Exit")){
                    System.exit(0);
                }
            }
		}
	}

	//updates the displayed table after a change (click)
	private void refresh(){
		//colouring the cells
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(game.isCovered(i, j)){
					if(game.isMarked(i, j)) {
						//marked cells are green
						field[i][j].setBackground(Color.GREEN);
					}
					else {
						//uncovered cells are gray
						field[i][j].setBackground(Color.DARK_GRAY);
					}
					field[i][j].setText("");
				}
				else {
					if (game.isMine(i, j)) {
						//bombs are red
						field[i][j].setBackground(Color.RED);
					} else {
						//the uncovered cells are white
						field[i][j].setBackground(Color.WHITE);
						if (game.getValue(i, j) > 0) {
							//displays the value of the cell (with the number of the neigbours which containing mines)
							field[i][j].setText(String.valueOf(game.getValue(i, j)));
						}
					}
				}
			}
		}
		//updating the counter of the marked cells
		mines.setText(number_of_mines + "/" + mines_left);
	}
	
	public class ClickListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						if(e.getSource() == field[i][j]) {
							if(game.isCovered(i,j) && !game.isMarked(i, j)) {
								//the clicked cell is a mine -> game over and lose
								//everything is revealed
								if(game.isMine(i, j)){
									game.revealAll();
									gameOver = true;
									win = false;
								}
								else {
									if(game.discover(i,j)){
										//if only the mines are covered and everything else is uncovered
										//then game over -> win
										game.setTime();
										gameOver = true;
										win = true;
									}
								}
							}
						}
					}
				}
				refresh();
				//if the game is over opens the gameover frame
				if(gameOver) {
					GameOverFrame gof = new GameOverFrame();
					gof.setVisible(true);
				}
			}
			
			else if(e.getButton() == MouseEvent.BUTTON3) {
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						if(e.getSource() == field[i][j]) {
							if(game.isCovered(i, j)) {
								//if the cell marked unmarks it
								if (game.isMarked(i, j) && mines_left <= number_of_mines){
									game.unMark(i, j);
									//updating for the counter
									mines_left++;
								}
								//if the cell unmarked marks it
								else if(mines_left > 0){
									game.mark(i, j);
									//updating for the counter
									mines_left--;
								}
							}
						}
					}
				}
				//updating the board with the changes
				refresh();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// Auto-generated method stub
		}
	}

	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//starting a game with different levels
			if(e.getActionCommand().equals("Easy") || e.getActionCommand().equals("Normal") || e.getActionCommand().equals("Hard")) {
				//the easy level with 10 mines
				if(e.getActionCommand().equals("Easy")){
					number_of_mines = 10;
				}
				//the normal level with 20 mines
				else if(e.getActionCommand().equals("Normal")){
					number_of_mines = 20;
				}
				//the hard level with 30 mines
				else {
					number_of_mines = 30;
				}
				//changing back the parameters
				win = false;
				gameOver = false;
				//and creating a new game with the chosen level
				game = new Game(10, 10, number_of_mines);
				game.createGame();
				mines_left = number_of_mines;
			}
			//loads the last saved game back
			else if(e.getActionCommand().equals("Load")) {
				try {
					game.loadGame();
					number_of_mines = game.getMines();
					mines_left = number_of_mines - game.getMarked();
					gameOver = false;
					win = false;
				} catch (IOException ex) {
					JFrame errorFrame = new JFrame();
					JLabel error = new JLabel("File load failed");
					errorFrame.add(error);
					errorFrame.setSize(200,100);
					errorFrame.setVisible(true);
					errorFrame.setLocationRelativeTo(null);
				}
			}
			//saving the game
			else if(e.getActionCommand().equals("Save")) {
				try {
					game.saveGame();
				} catch (IOException ex) {
					JFrame errorFrame = new JFrame();
					JLabel error = new JLabel("File save failed");
					errorFrame.add(error);
					errorFrame.setSize(200,100);
					errorFrame.setVisible(true);
					errorFrame.setLocationRelativeTo(null);
				}
			}
			refresh();
		}
	}
}