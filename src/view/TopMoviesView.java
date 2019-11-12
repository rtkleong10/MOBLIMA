package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controller.DataManager;
import model.Movie;

public class TopMoviesView extends View {
	
	public void start() {
		while (true) {
			int option = IOController.getMenuOption(
				"Top 5 movies by...",
				"Ticket sales",
				"Overall reviewer's rating",
				"Exit"
			);
			
			switch (option) {
				case 1:
					printTopMoviesByTicketSales();
					break;
					
				case 2:
					printTopMoviesByOverallRating();
					break;
					
				case 3:
					exit();
					return;
			}
		}
	}
	
	private void printTopMoviesByTicketSales() {
		System.out.println();
		
		System.out.println("Top 5 Movies By Ticket Sales");
		
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		if (movieList.size() == 0) {
			System.out.println("No movies available");
			return;
		}
		
		
		ArrayList<Movie> movieListCopy = new ArrayList<Movie>();
		movieListCopy.addAll(movieList);
		
		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieListCopy.sort(salesComparator);
		
		for (int i = 0; i < 5 && i < movieListCopy.size(); i++) {
			Movie movie = movieListCopy.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " ($" + String.format("%.02f", movie.getTotalSales()) + ")");
		}
		
		IOController.pressEnterToContinue();
		System.out.println();
	}
	
	private void printTopMoviesByOverallRating() {
		System.out.println();
		
		System.out.println("Top 5 Movies By Overall Rating");
		
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		ArrayList<Movie> moviesWithRatings = new ArrayList<Movie>();
		
		for (Movie movie: movieList) {
			if (movie.getOverallRating() != null)
				moviesWithRatings.add(movie);
		}
		
		if (moviesWithRatings.size() == 0) {
			System.out.println("No movies with ratings available");
			return;
		}
		
		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getOverallRating));
		moviesWithRatings.sort(ratingComparator);
		
		for (int i = 0; i < 5 && i < moviesWithRatings.size(); i++) {
			Movie movie = moviesWithRatings.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getOverallRating() + ")");
		}
		
		IOController.pressEnterToContinue();
		System.out.println();
	}
}
