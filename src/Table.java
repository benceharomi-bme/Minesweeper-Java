
public class Table {
	private int rows = 10;
	private int cols = 10;
	protected Cell[][] table;
	
	public Table(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.table = new Cell[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				table[i][j] = new Cell();
			}
		}
	}
	
	public void setMines(int num_of_bombs) {
		int bombs=0;
		while(bombs!=num_of_bombs) {
			int a =(int) (Math.random()*(rows));
			int b =(int)(Math.random()*(cols));
			if(!table[a][b].isMine()) {
				table[a][b].setMine();
				bombs++;
			}
		}
	}
	
	public void calculateValues() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(!table[i][j].isMine()) {
					int num = 0;
					for(int x = -1; x < 2; x++) {
						for(int y = -1; y < 2; y++) {
							if((i+x)>=0 && (j+y)>=0 && (i+x)<rows && (j+y)<cols) {
								if(table[i+x][j+y].isMine())
									num++;
							}
						}
					}
					table[i][j].setValue(num);
				}
			}
		}
	}

	public void revealAll(){
		for(int i = 0 ;i < rows; i++){
			for(int j = 0; j < cols; j++){
				table[i][j].uncover();
			}
		}
	}

	public void discover(int x, int y){
		if(x >= 0 && x < rows && y >= 0 && y < cols)
		if(table[x][y].isCovered()){
			if(table[x][y].getValue() == 0){
				table[x][y].uncover();
				if(x <= rows) discover(x + 1, y);
				if(x > 0) discover(x - 1, y);
				if(y <= cols) discover(x, y + 1);
				if(y >= 0 ) discover(x, y - 1);
			}
			else{
				table[x][y].uncover();
			}
		}
	}


	public Cell get(int row, int col){
		return table[row][col];
	}
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
	
}
