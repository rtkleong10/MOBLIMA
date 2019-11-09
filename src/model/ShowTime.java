package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ShowTime implements Serializable{
	private static final long serialVersionUID = 8096921810451802218L;
	
	private Cinema cinema;
	private LocalDateTime startTime;
	private Movie movie;
	private ArrayList <Booking> bookings;

	/**
	 * Creates ShowTime
	 * @param layout
	 * @param startTime
	 * @param movie
	 */
	public ShowTime(Cinema cinema, LocalDateTime startTime,  Movie movie) {
		this.cinema = cinema;
		this.bookings = new ArrayList<Booking>();
		this.startTime = startTime;
		this.movie = movie;
		movie.addShowTime(this);
	}
	
	/**
	 * Creates booking
	 * @param transactionId
	 * @param movieGoer
	 * @param selectedSeats
	 * @param price
	 */
	public void createBooking(String transactionId, MovieGoer movieGoer, boolean[][] selectedSeats, double price ){
		Booking newBooking = new Booking(transactionId, movieGoer, selectedSeats, price);
		this.bookings.add(newBooking);	
	}
	
	public SeatStatus[][] getSeatAvailabilities() {
		boolean [][] layout = getLayout();
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
	
	public boolean checkAvail( boolean [][] selectedSeat ) {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		for (int i =0; i<availSeat.length ; i++) {
			for (int j=0; j<availSeat[i].length; j++) {
				if(availSeat[i][j] == SeatStatus.TAKEN && selectedSeat[i][j] == true )
					return false;
				if(availSeat[i][j] == SeatStatus.NO_SEAT && selectedSeat[i][j] == true )
					return false;
			}
		}
		return true;
	}
	
	public boolean checkFull() {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		for (int i =0; i<availSeat.length ; i++) {
			for (int j=0; j<availSeat[i].length; j++) {
				if(availSeat[i][j] == SeatStatus.EMPTY )
					return false;
			}
		}
		return  true;
	}
	
	public LocalDate getDate() {
		return getStartTime().toLocalDate();
	}
	
	public boolean[][] getLayout() {
		return cinema.getLayout();
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}


	public Movie getMovie() {
		return movie;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	public double getTotalSales() {
		double totalSales = 0;
		
		for (Booking booking: bookings)
			totalSales += booking.getPrice();
		
		return totalSales;
	}
}