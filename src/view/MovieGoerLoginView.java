package view;

import model.*;

/**
 * This class displays the login and signup form for the movie goers
 */
public class MovieGoerLoginView {
	/**
	 * This method displays the signup form for the movie goers
	 * @return the newly signed up movie goer
	 */
	public static MovieGoer signupMovieGoer() {
		String username = IOController.readLine("Username: ");
		
		if (DataManager.getDataStore().checkMovieGoerUsername(username)) {
			IOController.displayMessage("Error: User with that username already exists");
			return null;
		}
		
		String name = IOController.readLine("Name: ");
		
		int mobileNumber = IOController.readInt("Mobile Number: ");
		String emailAddress = IOController.readLine("Email Address: ");
		
		String password1 = "", password2;
		
		while (true) {
			password1 = IOController.readPassword("Password: ");
			password2 = IOController.readPassword("Confirm Password: ");
			
			if (password1.equals(password2))
				break;
			else
				IOController.displayMessage("Error: Password mismatch");
		}
		
		MovieGoer movieGoer = new MovieGoer(username, name, mobileNumber, emailAddress, password1);
		
		if (!DataManager.getDataStore().addMovieGoer(movieGoer))
			IOController.displayMessage("Error: Unable to add movie goer");
		
		return movieGoer;
	}
	
	/**
	 * This method displays the login form for the movie goers
	 * @return the logged in movie goer
	 */
	public static MovieGoer loginMovieGoer() {
		String username = IOController.readLine("Username: ");
		
		if (!DataManager.getDataStore().checkMovieGoerUsername(username)) {
			IOController.displayMessage("Error: User with that that username doesn't exist");
			return null;
		}
		
		String password = IOController.readPassword("Password: ");
		
		MovieGoer movieGoer = DataManager.getDataStore().getMovieGoer(username, password);
		
		if (movieGoer == null)
			IOController.displayMessage("Error: Incorrect password");
		
		return movieGoer;
	}
}
