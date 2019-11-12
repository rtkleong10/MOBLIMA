package view;

import java.time.Duration;
import java.util.ArrayList;

import model.Movie;
import model.MovieGoer;
import model.ReviewRating;

public class MovieDetailsView extends View {
	private MovieGoer movieGoer;
	private Movie movie;
	
	public MovieDetailsView(MovieGoer movieGoer, Movie movie) {
		this.movieGoer = movieGoer;
		this.movie = movie;
	}
	
	public void start() {
		while (true) {
			int option = IOController.getMenuOption(
				this.movie.getTitle(),
				"View movie details",
				"View past reviews",
				"Add a review",
				"Exit"
			);
		
			switch (option) {
				case 1:
					printMovieDetails();
					break;
					
				case 2:
					printMovieReviews();
					break;
					
				case 3:
					addMovieReview();
					break;
					
				case 4:
					exit();
					return;
			}
		}
	}
	
	private void printMovieDetails() {
		System.out.println();
		
		Duration duration = movie.getDuration();
		String durationString = String.format("%dh %02dmin", duration.toHoursPart(), duration.toMinutesPart());
		
		System.out.println(
			"Title: " + movie.getTitle() + "\n" +
			"Synopsis: " + movie.getSynopsis() + "\n" +
			"Director: " + movie.getDirector() + "\n" +
			"Duration: " + durationString + "\n" +
			"Showing Status: " + movie.getShowingStatus().getLabel() + "\n" +
			"Release Rating: " + movie.getReleaseRating() + "\n" +
			"Movie Type: " + movie.getMovieType().getLabel()
		);
		
		System.out.println("Cast:");
		for (String castName: movie.getCast())
			System.out.println("• " + castName);
		
		System.out.println("Overall Reviewer Rating: " + (movie.getOverallRating() != null ? movie.getOverallRating() : "Not Available"));
		
		IOController.pressEnterToContinue();
		System.out.println();
	}
	
	private void printMovieReviews() {
		System.out.println();
		
		System.out.println("Past Reviews & Reviewer's Ratings:");
		
		ArrayList<ReviewRating> reviewRatingsList = movie.getReviewRatings();
		
		if (reviewRatingsList.size() == 0) {
			System.out.println("No reviews available");
			
		} else {
			for (ReviewRating reviewRating: reviewRatingsList)
				System.out.println(reviewRating.getReview() + " (" + reviewRating.getRating() + "/5) —— " + reviewRating.getMovieGoer().getName());
		}
		
		IOController.pressEnterToContinue();
		System.out.println();
	}
	
	private void addMovieReview() {
		System.out.println();
		
		System.out.print("Review: ");
		String review = IOController.readLine();
		
		System.out.print("Rating: ");
		int rating = IOController.readInt();
		
		ReviewRating reviewRating = ReviewRating.createReviewRating(movieGoer, review, rating);
		
		if (reviewRating != null) {
			movie.getReviewRatings().add(reviewRating);
			System.out.println("Review & rating added");
			
		} else {
			System.out.println("Error: Rating out of range (ratings must be between " + ReviewRating.MIN_RATING + " and " + ReviewRating.MAX_RATING + ")");
		}
		
		System.out.println();
	}
}
