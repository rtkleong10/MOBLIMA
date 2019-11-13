package view;

import model.CinemaStaff;
import model.DataManager;

public class CinemaStaffLoginView {
	
	public static CinemaStaff loginCinemaStaff() {
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

		return cinemaStaff;
	}
}

