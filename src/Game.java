import java.io.IOException;

/** This class controls the table
 * @author Bence
 *
 */
public class Game {
	private Table table;
	private int number_of_mines;
	private int covered_cells;
	private int playerTime;
	private long startTime;

	/** The constructor of the class
	 * @param rows Integer with the number of the rows
	 * @param cols Integer with the number of columns
	 * @param number_of_mines Integer with the number of mines
	 */
	public Game(int rows, int cols, int number_of_mines) {
		this.number_of_mines = number_of_mines;
		covered_cells = rows*cols;
		this.table = new Table(rows, cols);
		this.startTime = System.currentTimeMillis();
	}

	/** Creates the game, sets the given number of mines, calculates the values of the cells
	 */
	public void createGame() {
		table.setMines(number_of_mines);
		table.calculateValues();
	}

	/** Sets the players time which equals to the time to win the game
	 */
	public void setTime(){
		playerTime = (int) ((System.currentTimeMillis() - startTime) / 1000);
	}
	
	
	/** The getter of the player's time
	 * @return Integer with the value of the player's time
	 */
	public int getTime(){
		return playerTime;
	}

	/** Calls the table's filesaver function
	 * @throws IOException
	 */
	public void saveGame() throws IOException {
		table.saveToFile();
	}

	/** Calls the table's fileloader function
	 * @throws IOException
	 */
	public void loadGame() throws IOException {
		table.loadFromFile();
	}

	
	/** Marks the cell with the  given position
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 */
	public void mark(int x, int y){
		table.mark(x, y);
	}

	/** Unmarks the cell with the given position
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 */
	public void unMark(int x, int y){
		table.unMark(x, y);
	}

	/** Returns with the value of the cell with the given position
	 * @param x Integer wit the number of rows
	 * @param y Integer with the number of columns
	 * @return Integer with the value of the cell
	 */
	public int getValue(int x, int y){
		return table.getValue(x,y);
	}

	/** Checks that the cell has got a mine or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if has a mine, false if not
	 */
	public boolean isMine(int x, int y){
		return table.isMine(x, y);
	}

	/** Checks that the cell with the given position is marked or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if marked, false if not
	 */
	public  boolean isMarked(int x, int y){
		return table.isMarked(x, y);
	}

	/** Checks that the cell with the given position is covered or not
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which true if covered, false if not
	 */
	public boolean isCovered(int x, int y){
		return table.isCovered(x,y);
	}

	
	/** Uncovers all of the cells
	 */
	public void revealAll(){
		table.revealAll();
	}

	/** Uncovers the neighbourly "non-mine" cells, and checks if there are any remaining
	 * @param x Integer with the number of rows
	 * @param y Integer with the number of columns
	 * @return Boolean which is true if the last non-mine cell is uncovered, false if not
	 */
	public boolean discover(int x, int y){
		covered_cells = table.discover(x, y);
		if(covered_cells == number_of_mines){
			return true;
		}
		else {
			return false;
		}
	}

	/** Counts the mines in the table
	 * @return Integer with the number of mines on the table
	 */
	public int getMines() {
		return table.getNumOfMines();
	}

	/** Counts the marked cells in the table
	 * @return Integer with the number of marked cells
	 */
	public int getMarked(){
		return table.getNumOfMarked();
	}

}
