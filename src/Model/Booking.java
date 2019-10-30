package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable{
	private static final long serialVersionUID = 3967681838005218902L;
	
	private ArrayList<Ticket> tickets;
	private String transactionId; 
	private double price;
	
	public Booking(boolean[][] selectedSeats, String transactionId, double price) {
		this.transactionId = transactionId;
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
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public double getPrice() {
		return price;
	}	
}
