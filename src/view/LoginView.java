package view;

import java.util.Scanner;

import controller.AccountManager;
import model.MovieGoer;

public class LoginView extends View {
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(
			"Welcome to MOBLIMA\n" +
			"1: Sign up as a Movie Goer\n" +
			"2: Login as a Movie Goer\n" +
			"3: Login as a Cinema Staff\n" +
			"4: Exit"
		);
		
		int option;
		
		do {
			System.out.print("Option: ");
			option = sc.nextInt();
			
			MovieGoer movieGoer;
			MovieGoerView movieGoerView;
			
			switch (option) {
				case 1:
					movieGoer = registerMovieGoer();
					System.out.println(movieGoer.getMobileNumber());
					movieGoerView = new MovieGoerView(movieGoer);
					movieGoerView.start();
					break;
					
				case 2:
					movieGoer = loginMovieGoer();
					movieGoerView = new MovieGoerView(movieGoer);
					movieGoerView.start();
					break;
					
				case 3:
					break;
					
				case 4:
					System.out.println("Thanks for using the MOBLIMA app!");
					break;
					
				default:
					System.out.println("Invalid option selected!");
					break;
			}
		} while (option != 4);
		
		sc.close();
	}
	
	private MovieGoer registerMovieGoer() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Name: ");
		String name = sc.next();
		
		System.out.print("MobileNumber: ");
		int mobileNumber = sc.nextInt();
		
		System.out.print("Email Address: ");
		String emailAddress = sc.next();
		
		String password1, password2;
		
		while (true) {
			System.out.print("Password: ");
			password1 = sc.next();
			
			System.out.print("Confirm Password: ");
			password2 = sc.next();
			
			if (password1.equals(password2)) {
				break;
			
			} else {
				System.out.println("Error: Password mismatch");
			}
		}
		
		sc.close();
		
		MovieGoer movieGoer = new MovieGoer(username, name, mobileNumber, emailAddress, password1);
		AccountManager.addMovieGoer(movieGoer);
		
		return movieGoer;
	}
	
	private MovieGoer loginMovieGoer() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		sc.close();
		
		return AccountManager.getMovieGoer(username, password);
	}
}
