package model;

import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 6163594143675954354L;
	/**
	 * row of the seat 
	 */
	private int row;
	/**
	 * column of the seat
	 */
	private int col;
	
	/**
	 * Creates a ticket object of the seat booked
	 * @param row row of the booked seat
	 * @param col column of the booked seat
	 */
	protected Ticket(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	 * 
	 * @return row of the booked seat
	 */
	public int getRow() {
		return row;
	}
	/**
	 * 
	 * @return column of the booked seat
	 */
	public int getCol() {
		return col;
	}
}
