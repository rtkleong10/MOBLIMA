package model;

import java.io.Serializable;

public class ReviewRating implements Serializable, LabelledItem {
	private static final long serialVersionUID = 1725162345547776214L;
	
	public static final int MIN_RATING = 1;
	public static final int MAX_RATING = 5;
	
	private MovieGoer movieGoer;
	private String review;
	private Integer rating;
	
	private ReviewRating() {}
	
	/**
	 * creates a review rating based on the details listed
	 * @param movieGoer 
	 * @param review
	 * @param rating 
	 * @return A ReviewRating object if valid, null otherwise
	 */
	public static ReviewRating createReviewRating(MovieGoer movieGoer, String review, int rating) {
		ReviewRating reviewRating = new ReviewRating();
		
		reviewRating.movieGoer = movieGoer;
		reviewRating.review = review;
		reviewRating.rating = rating;
		
		if (rating >= MIN_RATING && rating <= MAX_RATING)
			return reviewRating;
		else
			return null; 
	} 
	
	/**
	 * @return movie goer of type MovieGoer class
	 */
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	
	/**
	 * @return review provided
	 */
	public String getReview() {
		return review;
	}
	
	/**
	 * @return rating 
	 */
	public int getRating() {
		return rating;
	}
	
	public String getLabel() {
		return review + " (" + rating + "/5) —— " + movieGoer.getName();
	}
}
