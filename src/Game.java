
public class Game {
	private Table table;
	private int rows;
	private int cols;
	private int number_of_mines;
	
	public Game(int rows, int cols, int number_of_mines) {
		this.rows = rows;
		this.cols = cols;
		this.number_of_mines = number_of_mines;
	}
	
	public void createGame() {
		this.table = new Table(rows, cols);
		table.setMines(number_of_mines);
		table.calculateValues();
	}
}
