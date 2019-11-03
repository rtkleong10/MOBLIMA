package View;

import Controller.CineplexManager;
import Model.*;

public class MovieGoerView {
	private MovieGoer movieGoer;
	
	public MovieGoerView(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void getBookingHistory() {
		for (Cineplex cineplex: CineplexManager.getCineplexList()) {
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
						}
					}
				}
			}
		}
	}
}
