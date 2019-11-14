import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import beerregister.Beer;

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
	
	public void saveGame() {
		FileInputStream f = new FileInputStream(cmd[1]);
		ObjectInputStream is = new ObjectInputStream(f);
		try {
			for(int i = 0; i< rows; i++) {
				for(int j = 0; j < cols; j++) {
					table.table[i][j]
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to read object.");
		}
		is.close();
	}
	
	public void loadGame() {
		FileOutputStream fo = new FileOutputStream(cmd[1]);
		ObjectOutputStream out = new ObjectOutputStream(fo);
		out.writeObject(beer_list);
		out.close();
	}
	
	public void draw() {
		for(int i = 0; i < rows; i++) {
			String s = "";
			for(int j = 0; j < cols; j++) {
				
				if(table.table[i][j].isCovered())
					s+="[ ]";
				else if(table.table[i][j].isMarked())
					s+="[!]";
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
