package controller;

import view.IOController;
import view.MenuView;

public class UserController implements Controller {
	public void start() {		
		int option = MenuView.getMenuOption(
			"Welcome to MOBLIMA!\nPlease select a portal",
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
