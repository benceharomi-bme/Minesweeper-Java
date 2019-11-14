import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Game {
	protected Table table;
	private int rows;
	private int cols;
	private int number_of_mines;
	
	public Game(int rows, int cols, int number_of_mines) {
		this.rows = rows;
		this.cols = cols;
		this.number_of_mines = number_of_mines;
		this.table = new Table(rows, cols);
	}
	
	public void createGame() {
		table.setMines(number_of_mines);
		table.calculateValues();
	}

	
	public void draw() {
		for(int i = 0; i < rows; i++) {
			String s = "";
			for(int j = 0; j < cols; j++) {
				
				if(table.table[i][j].isCovered()) {
					if (table.table[i][j].isMarked())
						s += "[!]";
					else
						s += "[ ]";
				}
				else if(!table.table[i][j].isCovered()) {
					if(table.table[i][j].isMine())
						s+="[*]";
					else
						s+=("["+ table.table[i][j].getValue() + "]");
				}
			}
			System.out.println(s);
		}
	}
}
