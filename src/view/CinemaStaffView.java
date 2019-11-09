package view;

import controller.DataManager;
import model.CinemaStaff;

public class CinemaStaffView extends View {
	private CinemaStaff cinemaStaff;
	
	public void start() {
		if (cinemaStaff == null)
			loginCinemaStaff();
		else
			displayMenu();
	}
	
	private void loginCinemaStaff() {
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		this.cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (this.cinemaStaff != null) {
			System.out.println("");
			displayMenu();
			
		} else {
			System.out.println("Error: Username or password is wrong");
			exit();
		}
	}
	
	private void displayMenu() {
		int option = getMenuOption(
			"What would you like to do?",
			"Modify Movie Listings",
			"Modify Cinema Showtimes",
			"Modify  Ticket Pricing Scheme",
			"List Top 5 Movies",
			"Exit"
		);
		
		switch (option) {
			case 1:
				load(new MovieListingsView());
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				load(new TopMoviesView());
				break;
				
			case 5:
				exit();
				break;
		}
	}
}