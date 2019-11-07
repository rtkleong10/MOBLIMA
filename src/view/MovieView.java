package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controller.CineplexManager;
import model.Movie;
import model.ReviewRating;

public class MovieView {
	public void printMovieDetails(Movie movie) {
		System.out.println(
			"Title: " + movie.getTitle() + "\n" +
			"Showing Status: " + movie.getShowingStatus() + "\n" +
			"Synopsis: " + movie.getSynopsis() + "\n" +
			"Director: " + movie.getDirector() + "\n"
		);
		
		System.out.println("Cast:");
		for (String castName: movie.getCast())
			System.out.println("- " + castName);
		
		System.out.println("Overall Reviewer Rating: " + movie.getOverallRating());
		
		System.out.println("Past Reviews & Reviewer's Ratings");
		for (ReviewRating reviewRating: movie.getReviewRatings()) {
			System.out.print("Rating: " + reviewRating.getRating());
			System.out.print(reviewRating.getReview());
			System.out.println("— " + reviewRating.getMovieGoer().getName());
			System.out.println("");
		}
	}
	
	public void printTopMoviesByOverallRating() {
		ArrayList<Movie> movieList = CineplexManager.getMovieList();
		Comparator<Movie> ratingComparator = Collections.reverseOrder(Comparator.comparing(Movie::getOverallRating));
		movieList.sort(ratingComparator);
		
		for (int i = 0; i < 5; i++) {
			Movie movie = movieList.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getOverallRating() + ")");
		}
	}
	
	public void printTopMoviesByTotalSales() {
		ArrayList<Movie> movieList = CineplexManager.getMovieList();
		Comparator<Movie> salesComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTotalSales));
		movieList.sort(salesComparator);
		
		for (int i = 0; i < 5; i++) {
			Movie movie = movieList.get(i);
			System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getTotalSales() + ")");
		}
	}
}
