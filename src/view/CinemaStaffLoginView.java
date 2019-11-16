package view;

import model.CinemaStaff;
import model.DataManager;

/**
 * This class displays the login form for the cinema staff
 */
public class CinemaStaffLoginView {
	
	/**
	 * This method displays the login form for the cinema staff
	 * @return the logged in cinema staff
	 */
	public static CinemaStaff loginCinemaStaff() {
		String username = IOController.readLine("Username: ");
		
		if (!DataManager.getDataStore().checkCinemaStaffUsername(username)) {
			IOController.displayMessage("Error: User with that that username doesn't exist");
			return null;
		}
		
		String password = IOController.readPassword("Password: ");
		
		CinemaStaff cinemaStaff = DataManager.getDataStore().getCinemaStaff(username, password);
		
		if (cinemaStaff == null)
			IOController.displayMessage("Error: Incorrect password");

		return cinemaStaff;
	}
}

