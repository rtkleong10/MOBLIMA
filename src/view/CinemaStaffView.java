package view;

import controller.DataManager;
import model.CinemaStaff;

public class CinemaStaffView extends View {
	private CinemaStaff cinemaStaff;
	
	public void start() {
		while (this.cinemaStaff == null) {
			int option = getMenuOption(
				"Please select an option",
				"Login",
				"Exit"
			);
			
			switch (option) {
				case 1:
					loginCinemaStaff();
					break;
					
				case 3:
					exit();
					return;
			}
		}
		
		displayMenu();
	}
	
	private void loginCinemaStaff() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		this.cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (this.cinemaStaff == null)
			System.out.println("Error: Username or password is wrong");
		
		System.out.println();
	}
	
	private void displayMenu() {
		int option = getMenuOption(
			"What would you like to modify?",
			"Movie Listings",
			"Cineam Showtimes",
			"Ticket Pricing Scheme",
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
				load(new TopMoviesView());
				break;
				
			case 5:
				exit();
				break;
		}
	}
}
