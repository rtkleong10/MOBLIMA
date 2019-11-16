package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.DataManager;
import model.Movie;
import view.ListView;
import view.MenuView;

/**
 * This class controls the display of the top 5 movies by ticket sales and overall reviewer's rating
 */
public class TopMoviesController implements Controller{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"Top 5 movies by...",
				"Ticket sales",
				"Overall reviewer's rating",
				"Exit"
			);
			
			List<String> movieStrings;
			
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
	
	/**
	 * This method determines the top 5 movies by ticket sales and stores the information in the form of a list of strings.
	 * @return a {@code List} of {@code String} objects containing the information about the top 5 movies by ticket sales
	 */
	private List<String> getTopMoviesByTicketSales() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		
		List<Movie> movieListCopy = new ArrayList<Movie>();
		movieListCopy.addAll(movieList);
		
		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieListCopy.sort(salesComparator);
		
		List<String> movieStrings = new ArrayList<String>(); 
		
		for (int i = 0; i < 5 && i < movieListCopy.size(); i++) {
			Movie movie = movieListCopy.get(i);
			movieStrings.add((i + 1) + ". " + movie.getTitle() + " ($" + String.format("%.2f", movie.getTotalSales()) + ")");
		}
		
		return movieStrings;
	}
	
	/**
	 * This method determines the top 5 movies by overall reviewer's rating and stores the information in the form of a list of strings.
	 * @return a {@code List} of {@code String} objects containing the information about the top 5 movies by overall reviewer's rating
	 */
	private List<String> getTopMoviesByOverallRating() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		List<Movie> moviesWithRatings = new ArrayList<Movie>();
		
		for (Movie movie: movieList) {
			if (movie.getOverallRating() != null)
				moviesWithRatings.add(movie);
		}
		
		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getOverallRating));
		moviesWithRatings.sort(ratingComparator);
		
		List<String> movieStrings = new ArrayList<String>();
		
		for (int i = 0; i < 5 && i < moviesWithRatings.size(); i++) {
			Movie movie = moviesWithRatings.get(i);
			movieStrings.add((i + 1) + ". " + movie.getTitle() + " (" + String.format("%.2f", movie.getOverallRating()) + ")");
		}
		
		return movieStrings;
	}
}
