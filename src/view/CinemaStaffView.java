package view;

import model.CinemaStaff;
import model.DataManager;

public class CinemaStaffView {
	
	public static CinemaStaff loginCinemaStaff() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = IOController.readLine();
		
		if (!DataManager.getDataStore().checkCinemaStaffUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			System.out.println();
			return null;
		}
		
		System.out.print("Password: ");
		String password = IOController.readLine();
		
		CinemaStaff cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (cinemaStaff == null)
			System.out.println("Error: Incorrect password");
		
		System.out.println();

		return cinemaStaff;
	}
	
	private void displayMenu() {
		int option = IOController.getMenuOption(
			"What would you like to do?",
			"Modify Movie Listings",
			"Modify Cinema Showtimes",
			"Modify Ticket Pricing Scheme",
			"List Top 5 Movies",
			"Exit"
		);
		
		switch (option) {
			case 1:
				load(new MovieListingsView());
				break;
				
			case 2:
				load(new CinemaShowtimesView());
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

