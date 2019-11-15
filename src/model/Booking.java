package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable {
	private static final long serialVersionUID = 3967681838005218902L;
	
	private String transactionId; 
	private MovieGoer movieGoer;
	private ArrayList<Ticket> tickets;
	private double price;
	
	public Booking(String transactionId, MovieGoer movieGoer, boolean[][] selectedSeats, double price) {
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
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public double getPrice() {
		return price;
	}	
}
