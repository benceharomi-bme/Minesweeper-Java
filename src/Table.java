
public class Table {
	private int x = 10;
	private int y = 10;
	private Cell[][] table;
	
	public Table(int x, int y) {
		this.x = x;
		this.y = y;
		this.table = new Cell[x][y];
	}
	
	
	public void setMines(int quantity) {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				table[i][j].setMine();
			}
		}
	}
	
	public void calculateValues() {
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}
