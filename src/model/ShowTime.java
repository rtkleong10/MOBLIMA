package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains all the information of a show time
 */
public class ShowTime implements Serializable, LabelledItem, BookShow {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 8096921810451802218L;
	
	/**
	 * The cinema of the show time
	 */
	private Cinema cinema;
	/**
	 * The start date and time of the show time
	 */
	private LocalDateTime startDateTime;
	/**
	 * The movie of the show time
	 */
	private Movie movie;
	/**
	 * The bookings made to the show time
	 */
	private ArrayList <Booking> bookings;

	/**
	 * Creates a {@code ShowTime} object for the given cinema, start date and time and movie
	 * @param cinema the cinema which the showtime belongs to
	 * @param startDateTime the start date time of the show time
	 * @param movie the movie of the showtime
	 */
	
	protected ShowTime(Cinema cinema, LocalDateTime startDateTime, Movie movie) {
		this.cinema = cinema;
		this.bookings = new ArrayList<Booking>();
		this.startDateTime = startDateTime;
		this.movie = movie;
		movie.addShowTime(this);
	}
	
	/**
	 * Creates a {@code Booking} object for the given movie goer, selected seats and price. This booking is added to this show time
	 * @param movieGoer the movie goer who made the booking
	 * @param selectedSeats the seats selected by the movie goer
	 * @param price the price of the booking transaction
	 * @return the newly created {@code Booking} object
	 */
	public Booking createBooking(MovieGoer movieGoer, boolean[][] selectedSeats, double price ){
		Booking newBooking = new Booking(createTransactionId(), movieGoer, selectedSeats, price);
		this.bookings.add(newBooking);
		return newBooking;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkAvail(boolean [][] selectedSeat) {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		
		for (int i = 0; i < availSeat.length; i++) {
			for (int j = 0; j < availSeat[i].length; j++) {
				SeatStatus seatStatus = availSeat[i][j];
				
				if (selectedSeat[i][j] == true && (seatStatus == SeatStatus.TAKEN || seatStatus == SeatStatus.NO_SEAT))
					return false;
			}
		}
		
		return true;
	}

	/**
	 * This method check if the show time is already fully booked
	 * @return true if the showtime is already fully booked, otherwise false
	 */
	public boolean checkFull() {
		SeatStatus[][] availSeat = this.getSeatAvailabilities();
		for (int i = 0; i < availSeat.length; i++) {
			for (int j = 0; j < availSeat[i].length; j++) {
				if(availSeat[i][j] == SeatStatus.EMPTY)
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * This method returns the total sales of a show time
	 * @return the total sales of the showtime
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (Booking booking: bookings)
			totalSales += booking.getPrice();
		
		return totalSales;
	}
	
	/**
	 * This method generates a transaction id for a booking
	 * @return the booking transaction id 
	 */
	public String createTransactionId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = cinema.getCinemaCode() + LocalDateTime.now().format(formatter);
		return transaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return this.getDate() + "  " + this.getStartDateTime().toLocalTime() + " (" + this.getCinema().getCinemaCode() + ")";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean[][] getLayout() {
		return cinema.getLayout();
	}
	
	/**
	 * This method returns the start date of a showtime
	 * @return the start date of the showtime
	 */
	public LocalDate getDate() {
		return startDateTime.toLocalDate();
	}
	
	/**
	 * This method returns the start date and time of the showtime
	 * @return the start date and time of the showtime
	 */
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	/**
	 * This method return the movie of the showtime
	 * @return the movie of the showtime
	 */
	public Movie getMovie() {
		return movie;
	}
	
	/**
	 * This method return all the bookings made to the showtime
	 * @return all the bookings made to the showtime
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	/**
	 * This method return the cinema of the showtime
	 * @return cthe inema of the showtime
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * This method sets the movie of the showtime
	 * @param movie the new movie of the showtime
	 */
	public void setMovie(Movie movie) {
		this.movie.getShowTimes().remove(this);
		this.movie = movie;
		movie.addShowTime(this);
	}

	/**
	 * This method sets the start date and time of the showtime
	 * @param startDateTime the start date and time of the showtime
	 */
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * This method removes the showtime
	 */
	public void remove() {
		this.movie.getShowTimes().remove(this);
		this.cinema.getShowTimes().remove(this);
	}
}
