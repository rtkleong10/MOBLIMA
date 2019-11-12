package view;

import java.util.ArrayList;

import controller.DataManager;
import model.Movie;
import model.MovieGoer;

public class MovieSelectView extends View {
private MovieGoer movieGoer;
	
	public MovieSelectView(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
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
			load(new MovieDetailsView(movieGoer, movieList.get(option)));
		else
			exit();
	}
}
