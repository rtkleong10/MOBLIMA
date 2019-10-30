package Model;

import java.io.Serializable;
import java.util.*;

public class Booking implements Serializable{
	private ArrayList <Ticket> tickets;
	private String transaction; 
	private double price;
	
	public Booking(int [][] bookSeat,String transaction, double price) {
		this.transaction = transaction;
		this.price = price;
		this.tickets = new ArrayList <Ticket> ();
		for (int i = 0; i< bookSeat.length ; i++) {
			for(int j =0; j<bookSeat[0].length; j++) {
				if(bookSeat[i][j]==2) {
					Ticket newTicket = new Ticket(i,j);
					tickets.add(newTicket);
				}
			}
		}
	}
	
	public ArrayList <Ticket> getTicket() {
		return tickets;
	}
}
