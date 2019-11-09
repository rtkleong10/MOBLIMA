
package view;

import controller.DataManager;
import model.*;

public class MovieGoerView extends View {
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
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				load(new CineplexSelectView(movieGoer));
				break;
				
			case 5:
				load(new BookingHistoryView(movieGoer));
				break;
				
			case 6:
				load(new TopMoviesView());
				break;
				
			case 7:
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
		
		while (true) {
			System.out.print("Password: ");
			password1 = sc.next();
			
			System.out.print("Confirm Password: ");
			password2 = sc.next();
			
			if (password1.equals(password2))
				break;
			else
				System.out.println("Error: Password mismatch");
		}
		
		this.movieGoer = new MovieGoer(username, name, mobileNumber, emailAddress, password1);
		
		if (DataManager.getDataStore().addMovieGoer(this.movieGoer)) {
			System.out.println("");
			displayMenu();
			
		} else {
			System.out.println("Error: User already exists");
			exit();
		}
	}
	
	private void loginMovieGoer() {
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		this.movieGoer = DataManager.getDataStore().getMovieGoer(username, password);
		
		if (this.movieGoer != null) {
			System.out.println("");
			displayMenu();
			
		} else {
			System.out.println("Error: Username or password is wrong");
			exit();
		}
	}
}
