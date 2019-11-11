package view;

import java.util.ArrayList;

import controller.BookingController;
import model.Cineplex;
import model.Movie;
import model.MovieGoer;
import model.ShowTime;

public class BookingShowTimeSelectView extends View {
	private MovieGoer movieGoer;
	private Cineplex cineplex;
	private Movie movie;
	
	public BookingShowTimeSelectView(MovieGoer movieGoer, Cineplex cineplex, Movie movie) {
		this.movieGoer = movieGoer;
		this.cineplex = cineplex;
		this.movie = movie;
	}
	
	public void start() {
		ArrayList<ShowTime> showTimeList = BookingController.getShowTimeList(cineplex, movie);
		int size = showTimeList.size();
		String[] showTimeStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			showTimeStrings[i] = showTimeList.get(i).getDate() + "  " + showTimeList.get(i).getStartTime().toLocalTime();
		}
		
		showTimeStrings[size] = "Exit";
		
		int option = IOController.getMenuOption(
			"Select a movie",
			showTimeStrings
		);
		
		option--;
		
		if (option != size)
			load(new BookingSeatSelectView(movieGoer, showTimeList.get(option)));
		else
			exit();
	}
}
