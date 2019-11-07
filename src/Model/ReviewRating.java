package Model;

import java.io.Serializable;

public class ReviewRating implements Serializable {
	private static final long serialVersionUID = 1725162345547776214L;
	
	private static final int MIN_RATING = 1;
	private static final int MAX_RATING = 5;
	private static final int DEFAULT_RATING = 3;
	
	private MovieGoer movieGoer;
	private String review;
	private Integer rating;
/**
 * creates a review rating based on the details listed
 * @param movieGoer 
 * @param review
 * @param rating 
 */
	public ReviewRating(MovieGoer movieGoer, String review, int rating) {
		this.movieGoer = movieGoer;
		this.review = review;
		
		if (rating >= MIN_RATING && rating <= MAX_RATING)
			this.rating = rating;
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
}