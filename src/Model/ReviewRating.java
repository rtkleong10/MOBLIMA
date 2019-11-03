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
	
	public ReviewRating(MovieGoer movieGoer, String review, int rating) {
		this.movieGoer = movieGoer;
		this.review = review;
		
		if (rating >= MIN_RATING && rating <= MAX_RATING)
			this.rating = rating;
	} 
	
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	
	public String getReview() {
		return review;
	}
	
	public int getRating() {
		return rating;
	}
}
