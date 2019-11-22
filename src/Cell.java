
public class Cell {
	private boolean mine = false;
	private boolean covered = true;
	private boolean marked = false;
	private int value;
	
	public Cell() {
		this.mine = false;
		this.covered = true;
		this.marked = false;
	}
	
	public void uncover() {
		this.covered = false;	
	}
	
	public void mark() {
		this.marked = !this.marked;
	}
	
	public void setMine() {
		this.mine = true;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public boolean isMine() {
		return mine;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public boolean isCovered() {
		return covered;
	}
}
