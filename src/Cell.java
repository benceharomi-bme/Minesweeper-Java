import java.io.Serializable;

/** The class which stores the data of the cells
 * @author Bence
 *
 */
@SuppressWarnings("serial")
public class Cell implements Serializable {
	private boolean mine;
	private boolean covered;
	private boolean marked;
	private int value;

	/**Sets the cells variables to the default values
	 */
	public Cell() {
		this.mine = false;
		this.covered = true;
		this.marked = false;
	}

	/**Uncovers the cell
	 */
	public void uncover() {
		this.covered = false;
		this.marked = false;
	}

	/** Marks the cell
	 */
	public void mark() {
		this.marked = true;
	}

	/** Unmarks the cell
	 */
	public void unMark(){ this.marked = false; }

	/** Sets a mine to the cell
	 */
	public void setMine() {
		this.mine = true;
	}

	/** Sets the value of the cell to the given argument
	 * @param value The value of the cell
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/** Gets the value of the cell
	 * @return Integer with the value of the cell
	 */
	public int getValue() {
		return this.value;
	}

	/** Checks if the cell contains a mine or not
	 * @return Boolean which true if contains a mine, false if not
	 */
	public boolean isMine() {
		return mine;
	}

	/** Checks if the cell is marked or not
	 * @return Boolean which true if the cell is marked, false if not
	 */
	public boolean isMarked() {
		return marked;
	}

	/** Checks if the cell is covered or not
	 * @return Boolean which true if the cell is covered, false if not
	 */
	public boolean isCovered() {
		return covered;
	}
}
