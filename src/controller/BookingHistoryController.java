package controller;

import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.DataManager;
import model.MovieGoer;
import model.ShowTime;
import view.ListView;

/**
 * This class controls the display of the booking history of a movie goer
 */
public class BookingHistoryController implements Controller {
	/**
	 * The movie goer for which the booking history is displayed
	 */
	private MovieGoer movieGoer;
	
	/**
	 * Creates a new {@code BookingHistoryController} object for the given movie goer
	 * @param movieGoer the movie goer for which the booking history is displayed
	 */
	public BookingHistoryController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		ListView.displayList("Booking History", getBookingHistory(), "No bookings made");
		NavigationController.goBack();
	}
	
	/**
	 * This method iterates through the cineplexes, cinemas, showtimes and bookings to find the bookings of the movie goer. Then, it returns the information of the booking as a string
	 * @return a {@code List} of {@code String} objects containing the information about each of the movie goer's bookings
	 */
	public List<String> getBookingHistory() {
		List<String> bookingHistoryStrings = new ArrayList<String>();
		
		for (Cineplex cineplex: DataManager.getDataStore().getCineplexList()) {
			for (Cinema cinema: cineplex.getCinemas()) {
				for (ShowTime showTime: cinema.getShowTimes()) {
					for (Booking booking: showTime.getBookings()) {
						if (booking.getMovieGoer() == movieGoer) {
							bookingHistoryStrings.add(
								"Transaction ID: " + booking.getTransactionId() + "\n" +
								"Price: $" + String.format("%.2f", booking.getPrice()) + "\n" +
								"Movie: " + showTime.getMovie().getTitle() + "\n" +
								"Show time: " + showTime.getLabel() + "\n" +
								"Cineplex: " + cineplex.getName() + "\n"
							);
						}
					}
				}
			}
		}
		
		return bookingHistoryStrings;
	}
}
