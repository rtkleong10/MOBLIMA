package view;

import controller.DataManager;
import model.CinemaStaff;

public class CinemaStaffView extends View {
	private CinemaStaff cinemaStaff;
	
	public void start() {
		while (this.cinemaStaff == null) {
			int option = IOController.getMenuOption(
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
		String username = IOController.readLine();
		
		if (!DataManager.getDataStore().checkCinemaStaffUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			System.out.println();
			return;
		}
		
		System.out.print("Password: ");
		String password = IOController.readLine();
		
		this.cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (this.cinemaStaff == null)
			System.out.println("Error: Incorrect password");
		
		System.out.println();
	}
	
	private void displayMenu() {
		int option = IOController.getMenuOption(
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
