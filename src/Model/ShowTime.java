package Model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShowTime implements Serializable{
	private static final long serialVersionUID = 8096921810451802218L;
	
	private boolean[][] layout; // From the cinema when created
	private LocalDateTime startTime;
	private Duration duration;
	private Movie movie;
	private ArrayList <Booking> bookings;

	public ShowTime(boolean[][] layout, LocalDateTime startTime, Duration duration, Movie movie) {
		this.layout = layout;
		this.bookings = new ArrayList<Booking>();
		this.startTime = startTime;
		this.duration = duration;
		this.movie = movie;
	}
	
	public void createBooking(boolean[][] selectedSeats, String transactionId, double price ){
		Booking newBooking = new Booking(selectedSeats, transactionId,  price);
		this.bookings.add(newBooking);	
	}
	
	public SeatStatus[][] getSeatAvailabilities() {
		SeatStatus[][] seatAvail = new SeatStatus[layout.length][];
		
		for (int i = 0; i < layout.length; i++) {
			boolean[] row = layout[i];
			seatAvail[i] = new SeatStatus[row.length];
			
			for (int j = 0; j < row.length; j++) {
				seatAvail[i][j] = row[j] == true ? SeatStatus.EMPTY : SeatStatus.NO_SEAT;
			}
		}
		
		for (Booking booking: bookings) {
			for (Ticket t: booking.getTickets()) {
				int row = t.getRow();
				int col = t.getCol();
				
				if (seatAvail[row][col] == SeatStatus.EMPTY)
					seatAvail[row][col] = SeatStatus.TAKEN;
			}
		}
		
		return seatAvail;
	}

	public boolean[][] getLayout() {
		return layout;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public Duration getDuration() {
		return duration;
	}

	public Movie getMovie() {
		return movie;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
}
