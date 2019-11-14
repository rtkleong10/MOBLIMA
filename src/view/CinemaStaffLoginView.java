package view;

import model.CinemaStaff;
import model.DataManager;

public class CinemaStaffLoginView {
	
	public static CinemaStaff loginCinemaStaff() {
		String username = IOController.readLine("Username: ");
		
		if (!DataManager.getDataStore().checkCinemaStaffUsername(username)) {
			IOController.displayMessage("Error: User with that that username doesn't exist");
			return null;
		}
		
		String password = IOController.readLine("Password: ");
		
		CinemaStaff cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (cinemaStaff == null)
			IOController.displayMessage("Error: Incorrect password");

		return cinemaStaff;
	}
}

