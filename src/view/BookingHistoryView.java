package view;

import controller.DataManager;
import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.MovieGoer;
import model.ShowTime;

public class BookingHistoryView extends View {
	
	private MovieGoer movieGoer;
	
	public BookingHistoryView(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		displayBookingHistory();
		exit();
	}
	
	private void displayBookingHistory() {
		System.out.println("Booking history: ");
		
		boolean empty = true;
		
		for (Cineplex cineplex: DataManager.getDataStore().getCineplexList()) {
			for (Cinema cinema: cineplex.getCinemas()) {
				for (ShowTime showTime: cinema.getShowTimes()) {
					for (Booking booking: showTime.getBookings()) {
						if (booking.getMovieGoer() == movieGoer) {
							System.out.println(
								"Booking: " + booking.getTransactionId() + "\n" +
								"Price: $" + booking.getPrice() + "\n" +
								"Movie: " + showTime.getMovie() + "\n" +
								"Date & Time: " + showTime.getStartTime().toString() + "\n" +
								"Cinema: " + cinema.getCinemaCode() + "\n" +
								"Cineplex: " + cineplex.getName() + "\n"
							);
							
							empty = false;
						}
					}
				}
			}
		}
		
		if (empty)
			System.out.println("No bookings made");
		
		pressEnterToContinue();
	}
}
