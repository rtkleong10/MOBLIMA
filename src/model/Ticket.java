package model;

import java.io.Serializable;

/**
 * This class contains all the information of a ticket
 */
public class Ticket implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 6163594143675954354L;
	/**
	 * The row of the booked seat
	 */
	private int row;
	/**
	 * The column of the booked seat
	 */
	private int col;
	
	/**
	 * Creates a {@code Ticket} object of seat booked for the given row and column
	 * @param row row of the booked seat
	 * @param col column of the booked seat
	 */
	protected Ticket(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * This method returns the row of the booked seat
	 * @return the row of the booked seat
	 */
	public int getRow() {
		return row;
	}

	/**
	 * This method returns the column of the booked seat
	 * @return the column of the booked seat
	 */
	public int getCol() {
		return col;
	}
}
