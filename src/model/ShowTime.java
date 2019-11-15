package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowTime implements Serializable, Bookshow{
	private static final long serialVersionUID = 8096921810451802218L;
	
	private Cinema cinema;
	private LocalDateTime startTime;
	private Movie movie;
	private ArrayList <Booking> bookings;

	/**
	 * Creates ShowTime instance
	 * @param cinema cinema which the showtime belongs to
	 * @param startTime starting time of the show
	 * @param movie movie of the showtime
	 */
	public ShowTime(Cinema cinema, LocalDateTime startTime, Movie movie) {
		this.cinema = cinema;
		this.bookings = new ArrayList<Booking>();
		this.startTime = startTime;
		this.movie = movie;
		movie.addShowTime(this);
	}
	
	/**
	 * Creates Booking instance
	 * @param transactionId transaction id of the booking
	 * @param movieGoer account of user who made the booking
	 * @param selectedSeats seats selected by user to book
	 * @param price price of the booking transaction 
	 */
	public Booking createBooking(MovieGoer movieGoer, boolean[][] selectedSeats, double price ){
		Booking newBooking = new Booking(createTransactionId(), movieGoer, selectedSeats, price);
		this.bookings.add(newBooking);
		return newBooking;
	}
	/**
	 * Gets all seat available for the showtime
	 * @return 2D array of SeatStatus
	 */
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
	/**
	 * Checks the availability of the seats selected by user
	 * @param selectedSeat 2D boolean array indicating seat selected by user
	 * @return total number of seats selected by users if all seat is available; otherwise -1
	 */
	public int checkAvail( boolean [][] selectedSeat ) {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		int count =0;
		for (int i =0; i<availSeat.length ; i++) {
			for (int j=0; j<availSeat[i].length; j++) {
				if(availSeat[i][j] == SeatStatus.TAKEN && selectedSeat[i][j] == true )
					return -1;
				else if(availSeat[i][j] == SeatStatus.NO_SEAT && selectedSeat[i][j] == true )
					return -1;
				else if(availSeat[i][j] == SeatStatus.EMPTY && selectedSeat[i][j] == true )
					count++;
			}
		}
		return count;
	}
	/**
	 * Check if the showtime is already fully booked
	 * @return true if the showtime is already fully booked; otherwise false
	 */
	public boolean checkFull() {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		for (int i = 0; i < availSeat.length; i++) {
			for (int j = 0; j < availSeat[i].length; j++) {
				if(availSeat[i][j] == SeatStatus.EMPTY)
					return false;
			}
		}
		return  true;
	}
	
	/**
	 * 
	 * @return total sales of the showtime
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (Booking booking: bookings)
			totalSales += booking.getPrice();
		
		return totalSales;
	}
	
	/**
	 * Generates transaction id
	 * @return booking transaction id 
	 */
	public String createTransactionId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = cinema.getCinemaCode() + LocalDateTime.now().format(formatter);
		return transaction;
	}
	
	/**
	 * 
	 * @return layout of the cinema of the showtime
	 */
	public boolean[][] getLayout() {
		return cinema.getLayout();
	}
	
	/**
	 * 
	 * @return date of the showtime
	 */
	public LocalDate getDate() {
		return startTime.toLocalDate();
	}
	
	/**
	 * 
	 * @return date and time of the showtime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @return movie of the showtime
	 */
	public Movie getMovie() {
		return movie;
	}
	
	/**
	 * 
	 * @return ArrayList of all booking made for the showtime
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	/**
	 * 
	 * @return cinema of the showtime
	 */
	public Cinema getCinema() {
		return cinema;
	}
}