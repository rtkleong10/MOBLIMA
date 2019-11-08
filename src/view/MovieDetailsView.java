package view;

import model.Movie;
import model.ReviewRating;

public class MovieDetailsView extends View {
	
	private Movie movie;
	
	public void start() {
		printMovieDetails(movie);
	}
	
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
}
