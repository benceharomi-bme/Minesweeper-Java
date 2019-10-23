
public class Game {
	private Table table;
	private int x;
	private int y;
	private int number_of_mines;
	
	public void setParameters(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setMines(int number_of_mines) {
		this.number_of_mines = number_of_mines;
	}
	
	public void createGame() {
		this.table = new Table(x,y);
		table.setMines(number_of_mines);
		table.calculateValues();
		table.display();
	}
}
