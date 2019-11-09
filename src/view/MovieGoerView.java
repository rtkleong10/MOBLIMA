package view;

import controller.DataManager;
import model.*;

public class MovieGoerView extends View {
	private MovieGoer movieGoer;
	
	public void start() {
		while (this.movieGoer == null) {
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
					return;
			}
		}
		
		displayMenu();
	}
	
	private void signupMovieGoer() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = sc.next();
		
		if (DataManager.getDataStore().checkMovieGoerUsername(username)) {
			System.out.println("Error: User already exists");
			return;
		}
		
		System.out.print("Name: ");
		sc.skip("((?<!(?>\\R))\\s)*");
		String name = sc.nextLine();
		
		System.out.print("Mobile Number: ");
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
		
		if (!DataManager.getDataStore().addMovieGoer(this.movieGoer))
			System.out.println("Error: Unable to add movie goer");
		
		System.out.println();
	}
	
	private void loginMovieGoer() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		this.movieGoer = DataManager.getDataStore().getMovieGoer(username, password);
		
		if (this.movieGoer == null)
			System.out.println("Error: Username or password is wrong");
		
		System.out.println();
	}
	
	private void displayMenu() {
		int option = getMenuOption(
			"Welcome " + movieGoer.getName() + "!",
			"View movie showtimes",
			"Book a ticket",
			"View movie details",
			"List top 5 movies",
			"View booking history",
			"Exit"
		);
		
		switch (option) {
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				load(new MovieSelectView(movieGoer));
				break;
				
				
			case 4:
				load(new TopMoviesView());
				break;
				
			case 5:
				load(new BookingHistoryView(movieGoer));
				break;
				
			case 6:
				exit();
				break;
		}
	}
}
