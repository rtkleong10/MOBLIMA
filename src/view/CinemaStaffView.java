package view;

import controller.DataManager;
import model.CinemaStaff;

public class CinemaStaffView extends View {
	private static final int MAX_TRIES = 3;
	private CinemaStaff cinemaStaff;
	
	public void start() {
		if (cinemaStaff == null)
			loginCinemaStaff();
		else
			displayMenu();
	}
	
	private void loginCinemaStaff() {
		boolean successful = false;
		
		for (int i = 0; i < MAX_TRIES; i++) {
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			this.cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
			
			if (this.cinemaStaff != null) {
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
			"What would you like to modify?",
			"Movie Listings",
			"Cineam Showtimes",
			"Ticket Pricing Scheme",
			"List Top 5 Movies",
			"Exit"
		);
	}
}
