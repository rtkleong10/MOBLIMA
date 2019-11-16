package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the information of a booking
 */
public class Booking implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 3967681838005218902L;
	
	/**
	 * The transaction id of the booking
	 */
	private String transactionId; 
	/**
	 * The movie goer who made the booking
	 */
	private MovieGoer movieGoer;
	/**
	 * The tickets booked in the booking
	 */
	private ArrayList<Ticket> tickets;
	/**
	 * The price of the booking
	 */
	private double price;
	
	/**
	 * Creates a {@code Booking} object with the given transaction id, movie goer, selected seats and price
	 * @param transactionId the transaction id of the booking
	 * @param movieGoer the movie goer who made the booking
	 * @param selectedSeats the selected seats of the booking
	 * @param price the price of the booking
	 */
	protected Booking(String transactionId, MovieGoer movieGoer, boolean[][] selectedSeats, double price) {
		this.transactionId = transactionId;
		this.movieGoer = movieGoer;
		this.price = price;
		this.tickets = new ArrayList<Ticket>();
		
		for (int i = 0; i < selectedSeats.length ; i++) {
			for(int j = 0; j < selectedSeats[i].length; j++) {
				if(selectedSeats[i][j] == true) {
					Ticket newTicket = new Ticket(i, j);
					tickets.add(newTicket);
				}
			}
		}
	}
	
	/**
	 * This method returns the transaction id of the booking
	 * @return the transaction id of the booking
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**
	 * This method returns the movie goer who made the booking
	 * @return the movie goer who made the booking
	 */
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	
	/**
	 * This method returns the tickets booked in the booking
	 * @return the tickets booked in the booking
	 */
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * This method returns the price of the booking
	 * @return the price of the booking
	 */
	public double getPrice() {
		return price;
	}	
}
