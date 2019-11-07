package view;

import java.util.Scanner;

import controller.CineplexManager;
import model.*;

public class MovieGoerView extends View {
	private MovieGoer movieGoer;
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(
			"Welcome " + movieGoer.getName() + "!\n" +
			"1: Get booking history\n" +
			"2: Back"
		);
		
		int option;
		
		do {
			System.out.print("Option: ");
			option = sc.nextInt();
			
			switch (option) {
				case 1:
					getBookingHistory();
					break;
					
				case 2:
					System.out.println("Going back");
					break;
					
				default:
					System.out.println("Invalid option selected!");
					break;
			}
		} while (option != 2);
		
		sc.close();
	}
	
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
