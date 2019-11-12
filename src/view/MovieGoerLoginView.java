package view;

import model.*;

public class MovieGoerLoginView {
	public static MovieGoer signupMovieGoer() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = IOController.readLine();
		
		if (DataManager.getDataStore().checkMovieGoerUsername(username)) {
			System.out.println("Error: User with that username already exists");
			System.out.println();
			return null;
		}
		
		System.out.print("Name: ");
		String name = IOController.readLine();
		
		System.out.print("Mobile Number: ");
		int mobileNumber = IOController.readInt();
		
		System.out.print("Email Address: ");
		String emailAddress = IOController.readLine();
		
		String password1 = "", password2;
		
		while (true) {
			System.out.print("Password: ");
			password1 = IOController.readLine();
			
			System.out.print("Confirm Password: ");
			password2 = IOController.readLine();
			
			if (password1.equals(password2))
				break;
			else
				System.out.println("Error: Password mismatch");
		}
		
		MovieGoer movieGoer = new MovieGoer(username, name, mobileNumber, emailAddress, password1);
		
		if (!DataManager.getDataStore().addMovieGoer(movieGoer))
			System.out.println("Error: Unable to add movie goer");
		
		System.out.println();
		
		return movieGoer;
	}
	
	public static MovieGoer loginMovieGoer() {
		System.out.println();
		
		System.out.print("Username: ");
		String username = IOController.readLine();
		
		if (!DataManager.getDataStore().checkMovieGoerUsername(username)) {
			System.out.println("Error: User with that that username doesn't exist");
			System.out.println();
			return null;
		}
		
		System.out.print("Password: ");
		String password = IOController.readLine();
		
		MovieGoer movieGoer = DataManager.getDataStore().getMovieGoer(username, password);
		
		if (movieGoer == null)
			System.out.println("Error: Incorrect password");
		
		System.out.println();
		
		return movieGoer;
	}
}
