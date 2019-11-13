package controller;

import view.IOController;
import view.MenuView;

public class UserController implements Controller {
	public void start() {
		IOController.displayMessage("Welcome to MOBLIMA!");
		int option = MenuView.getMenuOption(
			"Please select a portal",
			"Movie Goer",
			"Cinema Staff",
			"Exit"
		);
		
		switch (option) {
			case 1:
				NavigationController.load(new MovieGoerController());
				break;
				
			case 2:
				NavigationController.load(new CinemaStaffController());
				break;
				
			case 3:
				IOController.displayMessage("Thanks for using the MOBLIMA app!");
				NavigationController.goBack();
				break;
		}
	}
}
