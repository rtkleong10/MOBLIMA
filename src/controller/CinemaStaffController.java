package controller;

import model.CinemaStaff;
import view.CinemaStaffLoginView;
import view.MenuView;

public class CinemaStaffController implements Controller {
	CinemaStaff cinemaStaff;
	
	@Override
	public void start() {
		while (this.cinemaStaff == null) {
			int option = MenuView.getMenuOption(
				"Please select an option",
				"Login",
				"Exit"
			);
			
			switch (option) {
				case 1:
					this.cinemaStaff = CinemaStaffLoginView.loginCinemaStaff();
					break;
					
				case 3:
					NavigationController.goBack();
					return;
			}
		}
		
		displayMenu();
	}
	
	private void displayMenu() {
		int option = MenuView.getMenuOption(
			"What would you like to do?",
			"Modify Movie Listings",
			"Modify Cinema Showtimes",
			"Modify Ticket Pricing Scheme",
			"List Top 5 Movies",
			"Exit"
		);
		
		switch (option) {
			case 1:
				NavigationController.load(new MovieEditController());
				break;
				
			case 2:
				NavigationController.load(new ShowTimeEditController());
				break;
				
			case 3:
				NavigationController.load(new PricingSchemeEditController());
				break;
				
			case 4:
				NavigationController.load(new TopMoviesController());
				break;
				
			case 5:
				NavigationController.goBack();
				break;
		}
	}
}
