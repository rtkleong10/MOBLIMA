package view;

import controller.DataManager;
import model.*;

public class MovieGoerView extends View {
	private static final int MAX_TRIES = 3;
	private MovieGoer movieGoer;
	
	public void start() {
		if (this.movieGoer == null)
			displayLoginMenu();
		else
			displayMenu();
	}
	
	private void displayLoginMenu() {
		int option = getMenuOption(
			"Please select an option",
			"Sign up",
			"Login",
			"Exit"
		);
		
		switch (option) {
			case 1:
				signupMovieGoer();
				break;
				
			case 2:
				loginMovieGoer();
				break;
				
			case 3:
				exit();
				break;
		}
	}
	
	private void signupMovieGoer() {
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		System.out.print("MobileNumber: ");
		int mobileNumber = sc.nextInt();
		
		System.out.print("Email Address: ");
		String emailAddress = sc.next();
		
		String password1 = "", password2;
		
		boolean successful = false;
		
		for (int i = 0; i < MAX_TRIES; i++) {
			System.out.print("Password: ");
			password1 = sc.next();
			
			System.out.print("Confirm Password: ");
			password2 = sc.next();
			
			if (password1.equals(password2)) {
				successful = true;
				break;
				
			} else {
				System.out.println("Error: Password mismatch");
			}
		}
		
		if (successful) {
			this.movieGoer = new MovieGoer(username, name, mobileNumber, emailAddress, password1);
			DataManager.getDataStore().addMovieGoer(this.movieGoer);
			
			System.out.println("");
			displayMenu();
			
		} else {
			System.out.println("Maximum tries reached");
			exit();
		}
	}
	
	private void loginMovieGoer() {
		boolean successful = false;
		
		for (int i = 0; i < MAX_TRIES; i++) {
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			this.movieGoer = DataManager.getDataStore().getMovieGoer(username, password);
			
			if (this.movieGoer != null) {
				successful = true;
				break;
				
			} else {
				System.out.println("Error: Username or password is wrong");
			}
		}	
		
		if (successful) {
			System.out.println("");
			displayMenu();
		
		} else {
			System.out.println("Maximum tries reached");
			exit();
		}
	}
	
	private void displayMenu() {
		int option = getMenuOption(
			"Welcome " + movieGoer.getName() + "!",
			"Search for a movie",
			"View movie details",
			"Check seat availabilities",
			"Book a ticket",
			"View booking history",
			"List Top 5 Movies",
			"Exit"
		);
		
		switch (option) {
			case 1:
				getBookingHistory();
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
//				load(new BookingView(this.movieGoer));
				break;
				
			case 6:
				load(new TopMoviesView());
				break;
				
			case 7:
				exit();
				break;
		}
	}
	
	public void getBookingHistory() {
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
						}
					}
				}
			}
		}
	}
}
