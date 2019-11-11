package view;


public class AppView extends View {
	
	public void start() {
		int option = getMenuOption(
			"Welcome to MOBLIMA!\nPlease select a portal",
			"Movie Goer",
			"Cinema Staff",
			"Exit"
		);
				
		switch (option) {
			case 1:
				load(new MovieGoerView());
				break;
				
			case 2:
				load(new CinemaStaffView());
				break;
				
			case 3:
				System.out.println("Thanks for using the MOBLIMA app!");
				exit();
				break;
		}
	}	
}
