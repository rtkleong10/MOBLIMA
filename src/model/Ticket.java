package model;

import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 6163594143675954354L;
	
	private int row;
	private int col;
	
	protected Ticket(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
