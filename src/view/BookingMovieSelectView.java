package view;

import java.util.ArrayList;

import controller.BookingController;
import model.Cineplex;
import model.Movie;
import model.MovieGoer;

public class BookingMovieSelectView extends View {
	private MovieGoer movieGoer;
	private Cineplex cineplex;
	
	public BookingMovieSelectView(MovieGoer movieGoer, Cineplex cineplex) {
		this.movieGoer = movieGoer;
		this.cineplex = cineplex;
	}
	
	public void start() {
		ArrayList<Movie> movieList = BookingController.getMovieList(cineplex);
		int size = movieList.size();
		String[] movieStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			movieStrings[i] = movieList.get(i).getTitle();
		}
		
		movieStrings[size] = "Exit";
		
		int option = IOController.getMenuOption(
			"Select a movie",
			movieStrings
		);
		
		option--;
		
		if (option != size)
			load(new BookingShowTimeSelectView(movieGoer, cineplex, movieList.get(option)));
		else
			exit();
	}
}
