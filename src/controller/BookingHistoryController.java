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

public class BookingHistoryController implements Controller {
	private MovieGoer movieGoer;
	
	public BookingHistoryController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	@Override
	public void start() {
		ListView.displayList("Booking History", getBookingHistory(), "No bookings made");
		NavigationController.goBack();
	}
	
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
