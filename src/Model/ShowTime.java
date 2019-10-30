package Model;

import java.io.Serializable;
import java.util.*;

public class ShowTime implements Serializable{
	private String name;
	private ArrayList <Booking> bookings; //(has-a)
	private int [][] layout; // From cinema when created

	public ShowTime(String name, int[][] layout) {
		this.name = name;
		this.layout = layout;
		this.bookings = new ArrayList<Booking>();
	}
	
	public void createBooking(int[][] bookSeat, String transaction, double price ){
		Booking newBooking = new Booking(bookSeat, transaction,  price);
		bookings.add(newBooking);
		
	}
	public int[][] getSeatAvailabilities() {

		int[][] seatAvail = layout.clone();
		
		for (Booking b: bookings) {
			for (Ticket t: b.getTicket()) {
				seatAvail[t.getRow()][t.getCol()] = 0;
			}
		}
		/*-1: No seat
		0: Taken
		1: Available*/
		return seatAvail;
	}
}
