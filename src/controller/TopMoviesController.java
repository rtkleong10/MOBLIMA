package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.DataManager;
import model.Movie;
import view.ListView;
import view.MenuView;

public class TopMoviesController implements Controller{
	
	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"Top 5 movies by...",
				"Ticket sales",
				"Overall reviewer's rating",
				"Exit"
			);
			
			ArrayList<String> movieStrings;
			
			switch (option) {
				case 1:
					movieStrings = getTopMoviesByTicketSales();
					ListView.displayList("Top 5 Movies By Ticket Sales", movieStrings, "No movies available");
					break;
					
				case 2:
					movieStrings = getTopMoviesByOverallRating();
					ListView.displayList("Top 5 Movies By Overall Rating", movieStrings, "No movies available");
					break;
					
				case 3:
					NavigationController.goBack();
					return;
			}
		}
	}
	
	private ArrayList<String> getTopMoviesByTicketSales() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		
		ArrayList<Movie> movieListCopy = new ArrayList<Movie>();
		movieListCopy.addAll(movieList);
		
		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieListCopy.sort(salesComparator);
		
		ArrayList<String> movieStrings = new ArrayList<String>(); 
		
		for (int i = 0; i < 5 && i < movieListCopy.size(); i++) {
			Movie movie = movieListCopy.get(i);
			movieStrings.add((i + 1) + ". " + movie.getTitle() + " ($" + String.format("%.02f", movie.getTotalSales()) + ")");
		}
		
		return movieStrings;
	}
	
	private ArrayList<String> getTopMoviesByOverallRating() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		ArrayList<Movie> moviesWithRatings = new ArrayList<Movie>();
		
		for (Movie movie: movieList) {
			if (movie.getOverallRating() != null)
				moviesWithRatings.add(movie);
		}
		
		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getOverallRating));
		moviesWithRatings.sort(ratingComparator);
		
		ArrayList<String> movieStrings = new ArrayList<String>();
		
		for (int i = 0; i < 5 && i < moviesWithRatings.size(); i++) {
			Movie movie = moviesWithRatings.get(i);
			movieStrings.add((i + 1) + ". " + movie.getTitle() + " (" + movie.getOverallRating() + ")");
		}
		
		return movieStrings;
	}
}
