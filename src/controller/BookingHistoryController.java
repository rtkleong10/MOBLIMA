package controller;

import java.util.ArrayList;

import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.DataManager;
import model.MovieGoer;
import model.ShowTime;
import view.IOController;
import view.ListView;

public class BookingHistoryController implements Controller {
	MovieGoer movieGoer;
	
	public BookingHistoryController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		ListView.displayList("Booking History", getBookingHistory(), "No bookings made");
		NavigationController.goBack();
	}
	
	public ArrayList<String> getBookingHistory() {
		ArrayList<String> bookingHistoryStrings = new ArrayList<String>();
		
		for (Cineplex cineplex: DataManager.getDataStore().getCineplexList()) {
			for (Cinema cinema: cineplex.getCinemas()) {
				for (ShowTime showTime: cinema.getShowTimes()) {
					for (Booking booking: showTime.getBookings()) {
						if (booking.getMovieGoer() == movieGoer) {
							bookingHistoryStrings.add(
								"Booking: " + booking.getTransactionId() + "\n" +
								"Price: $" + String.format("%.2f", booking.getPrice()) + "\n" +
								"Movie: " + showTime.getMovie().getTitle() + "\n" +
								"Date & Time: " + showTime.getLabel() + "\n" +
								"Cinema: " + cinema.getCinemaCode() + "\n" +
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
