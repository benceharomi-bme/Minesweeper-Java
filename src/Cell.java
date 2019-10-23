
public class Cell {
	private boolean mined = false;
	private enum state {
		COVERED,
		UNCOVERED,
		MARKED;
	}
	private state state_of_cell = state.COVERED;
	
	public Cell() {
		this.mined = false;
		state_of_cell = state.COVERED;	
	}
	
	public void uncover() {
		state_of_cell = state.UNCOVERED;	
	}
	
	public void mark() {
		state_of_cell = state.MARKED;
	}
	
	public void setMine() {
		this.mined = true;
	}
	
	public boolean isMined() {
		if(this.mined) {return true;}
		return false;
	}
	
	public void drawCell() {
		if(state_of_cell == state.COVERED) {
			
		}else if(state_of_cell == state.MARKED) {
			
		}else if(state_of_cell == state.UNCOVERED) {
			
		}
	}
}
