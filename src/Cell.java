
public class Cell {
	private boolean covered;
	private boolean mine;
	private boolean flag;
	private int value;
	
	public Cell() {
		this.covered = true;
		this.mine = false;
		this.flag = false;	
	}
	
	public void uncover() {
		this.covered = false;
	}
	
}
