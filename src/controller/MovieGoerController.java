package controller;

import model.MovieGoer;
import view.MenuView;
import view.MovieGoerLoginView;

public class MovieGoerController implements Controller {
	private MovieGoer movieGoer;
		
	public void start() {
		while (this.movieGoer == null) {
			int option = MenuView.getMenuOption(
				"Please select an option",
				"Sign up",
				"Login",
				"Exit"
			);
			
			switch (option) {
				case 1:
					this.movieGoer = MovieGoerLoginView.signupMovieGoer();
					break;
					
				case 2:
					this.movieGoer = MovieGoerLoginView.loginMovieGoer();
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
			"Welcome " + movieGoer.getName() + "!",
			"View movie showtimes",
			"Book a ticket",
			"View movie details",
			"List top 5 movies",
			"View booking history",
			"Exit"
		);
		
		switch (option) {
			case 1:
				break;
				
			case 2:
				NavigationController.load(new BookingController(movieGoer));
				break;
				
			case 3:
				NavigationController.load(new MovieListController(movieGoer));
				break;
				
				
			case 4:
				NavigationController.load(new TopMoviesController());
				break;
				
			case 5:
				NavigationController.load(new BookingHistoryController(movieGoer));
				break;
				
			case 6:
				NavigationController.goBack();
				break;
		}
	}
}
