package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controller.DataManager;
import model.Movie;

public class TopMoviesView extends View {
	
	public void start() {
		int option = getMenuOption(
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
				break;
		}
	}
	
	public void printTopMoviesByTicketSales() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieList.sort(salesComparator);
		
		for (int i = 0; i < 5; i++) {
			Movie movie = movieList.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getTotalSales() + ")");
		}
	}
	
	public void printTopMoviesByOverallRating() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getOverallRating));
		movieList.sort(ratingComparator);
		
		for (int i = 0; i < 5; i++) {
			Movie movie = movieList.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getOverallRating() + ")");
		}
	}
}
