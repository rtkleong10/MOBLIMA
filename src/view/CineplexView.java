package view;

import model.Cineplex;
import model.MovieGoer;

public class CineplexView extends View {
	
	private MovieGoer movieGoer;
	private Cineplex cineplex;
	
	public CineplexView(MovieGoer movieGoer, Cineplex cineplex) {
		this.movieGoer = movieGoer;
		this.cineplex = cineplex;
	}
	
	public void start() {
		int option = getMenuOption(
			"Welcome to " + cineplex.getName() + "!",
			"List showtimes",
			"Book a ticket"
		);
	}
}
