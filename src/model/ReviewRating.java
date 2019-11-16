package model;

import java.io.Serializable;

/**
 * This class contains all the information of a review rating
 */
public class ReviewRating implements Serializable, LabelledItem {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 1725162345547776214L;
	
	/**
	 * The minimum possible rating value
	 */
	public static final int MIN_RATING = 1;
	/**
	 * The maximum possible rating value
	 */
	public static final int MAX_RATING = 5;
	
	/**
	 * The movie goer who made the review rating
	 */
	private MovieGoer movieGoer;
	/**
	 * The review
	 */
	private String review;
	/**
	 * The rating
	 */
	private Integer rating;
	
	/**
	 * Creates a {@code ReviewRating} object
	 */
	private ReviewRating() {}
	
	/**
	 * Creates a {@code ReviewRating} object with the given movie goer, review and rating
	 * @param movieGoer the movie goer who made the review rating
	 * @param review the review
	 * @param rating the rating
	 * @return the {@code ReviewRating} object if it's valid, null otherwise
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
	 * This method returns the movie goer who made the review rating
	 * @return the movie goer who made the review rating
	 */
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	
	/**
	 * This method returns the review
	 * @return the review
	 */
	public String getReview() {
		return review;
	}
	
	/**
	 * This method returns the rating
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return review + " (" + rating + "/5) —— " + movieGoer.getName();
	}
}
