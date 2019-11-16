package view;

import java.time.Duration;

import model.Movie;
import model.MovieGoer;
import model.ReviewRating;

public class MovieView {
	
	public static void printMovieDetails(Movie movie) {
		Duration duration = movie.getDuration();
		String durationString = String.format("%dh %02dmin", duration.toHoursPart(), duration.toMinutesPart());
		
		IOController.displayMessage(
			"Title: " + movie.getTitle() + "\n" +
			"Synopsis: " + movie.getSynopsis() + "\n" +
			"Director: " + movie.getDirector() + "\n" +
			"Duration: " + durationString + "\n" +
			"Showing Status: " + movie.getShowingStatus().getLabel() + "\n" +
			"Release Rating: " + movie.getReleaseRating().getLabel() + "\n" +
			"Movie Type: " + movie.getMovieType().getLabel()
		);
		
		IOController.displayMessage("Cast:");
		for (String castName: movie.getCast())
			IOController.displayMessage("• " + castName);
		
		String overallRating = (movie.getOverallRating() != null) ? String.format("%.1f", movie.getOverallRating()) : "Not Available";
		IOController.displayMessage("Overall Reviewer Rating: " + overallRating);
		
		IOController.pressEnterToContinue();
	}
	public static void addMovieReview(Movie movie, MovieGoer movieGoer) {
		String review = IOController.readLine("Review: ");
		int rating = IOController.readInt("Rating: ");
		
		ReviewRating reviewRating = ReviewRating.createReviewRating(movieGoer, review, rating);
		
		if (reviewRating != null) {
			movie.getReviewRatings().add(reviewRating);
			IOController.displayMessage("Review & rating added");
			
		} else {
			IOController.displayMessage("Error: Rating out of range (ratings must be between " + ReviewRating.MIN_RATING + " and " + ReviewRating.MAX_RATING + ")");
		}
	}
}
