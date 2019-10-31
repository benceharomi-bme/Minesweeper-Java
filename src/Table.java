
public class Table {
	private int rows = 10;
	private int cols = 10;
	private Cell[][] table;
	
	public Table(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.table = new Cell[rows][cols];
	}
	
	
	public void setMines(int quantitcols) {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				table[i][j].setMine();
			}
		}
	}
	
	public void calculateValues() {
		
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
	
}
