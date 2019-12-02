import java.io.*;
import java.util.ArrayList;
import java.util.List;

/** Creates and checks the board and does the file saving/loading of the game
 * @author Bence
 *
 */
public class Table {
	private int rows = 10;
	private int cols = 10;
	private int covered;
	protected List<List<Cell>> table;

	/** Initialize the table with the given arguments
	 * @param rows Integer with the number of the rows
	 * @param cols Integer with the number of the columns
	 */
	public Table(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		covered = rows*cols;
		this.table = new ArrayList<List<Cell>>();
		for(int i = 0; i < rows; i++) {
			table.add(new ArrayList<Cell>());
			for(int j = 0; j < cols; j++) {
				table.get(i).add(new Cell());
			}
		}
	}
	
	/** Saves the list to a file
	 * @throws IOException
	 */
	public void saveToFile() throws IOException {
		FileOutputStream fo = new FileOutputStream("game_save");
		ObjectOutputStream out = new ObjectOutputStream(fo);
		out.writeObject(table);
		out.close();
	}
	
	/** Loads the list from the file
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public  void loadFromFile() throws IOException {
		FileInputStream f = new FileInputStream("game_save");
		ObjectInputStream is = new ObjectInputStream(f);
		try {
			table = (ArrayList<List<Cell>>) is.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to read object.");
		}
		is.close();
	}

	/** Places as many mines as the argument says
	 * @param num_of_bombs An integer with the number of the mines to place
	 */
	public void setMines(int num_of_bombs) {
		int bombs=0;
		while(bombs!=num_of_bombs) {
			int a =(int) (Math.random()*(rows));
			int b =(int)(Math.random()*(cols));
			if(!table.get(a).get(b).isMine()){
				table.get(a).get(b).setMine();
				bombs++;
			}
		}
	}
	
	/** Calculates the value (how many mines are in the neighbourly cells) of the non-mine cells
	 */
	public void calculateValues() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(!table.get(i).get(j).isMine()) {
					int num = 0;
					for(int x = -1; x < 2; x++) {
						for(int y = -1; y < 2; y++) {
							if((i+x)>=0 && (j+y)>=0 && (i+x)<rows && (j+y)<cols) {
								if(table.get(i+x).get(j+y).isMine())
									num++;
							}
						}
					}
					table.get(i).get(j).setValue(num);
				}
			}
		}
	}
	
	/** Uncovers the whole table (all of the cells)
	 */
	public void revealAll(){
		for(int i = 0 ;i < rows; i++){
			for(int j = 0; j < cols; j++){
				table.get(i).get(j).uncover();
			}
		}
	}

	/** Uncovers the neighbourly "non-mine" cells
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Integer with the number of the covered mines
	 */
	public int discover(int x, int y){
		if(table.get(x).get(y).isCovered()){
			if(table.get(x).get(y).getValue() == 0){
				table.get(x).get(y).uncover();
				covered--;
				for(int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if ((i + x) >= 0 && (j + y) >= 0 && (i + x) < rows && (j + y) < cols) {
							discover(i + x, j + y);
						}
					}
				}
			}
			else{
				table.get(x).get(y).uncover();
				covered--;
			}
		}
		return covered;
	}
	
	/** Marks the cell with the  given position
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 */
	public void mark(int x, int y){
		table.get(x).get(y).mark();
	}

	/** Unmarks the cell with the given position
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 */
	public void unMark(int x, int y){
		table.get(x).get(y).unMark();
	}

	/** Returns with the value of the cell with the given position
	 * @param x Integer wit the number of rows
	 * @param y Integer with the number of columns
	 * @return Integer with the value of the cell
	 */
	public int getValue(int x, int y){
		return table.get(x).get(y).getValue();
	}

	/** Checks that the cell has got a mine or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if has a mine, false if not
	 */
	public boolean isMine(int x, int y){
		return table.get(x).get(y).isMine();
	}

	/** Checks that the cell with the given position is marked or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if marked, false if not
	 */
	public boolean isMarked(int x, int y){
		return table.get(x).get(y).isMarked();
	}

	/** Checks that the cell with the given position is covered or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if covered, false if not
	 */
	public boolean isCovered(int x, int y){
		return table.get(x).get(y).isCovered();
	}

	/** Counts the mines in the table
	 * @return Integer with the number of mines on the table
	 */
	public int getNumOfMines(){
		int mines = 0;
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(table.get(i).get(j).isMine()){
					mines++;
				}
			}
		}
		return mines;
	}

	/** Counts the marked cells in the table
	 * @return Integer with the number of marked cells
	 */
	public int getNumOfMarked(){
		int mines = 0;
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(table.get(i).get(j).isMarked()){
					mines++;
				}
			}
		}
		return mines;
	}
}
