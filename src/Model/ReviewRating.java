package Model;

import java.io.Serializable;

public class ReviewRating implements Serializable {
	private static final long serialVersionUID = 1725162345547776214L;
	
	private static final int MIN_RATING = 1;
	private static final int MAX_RATING = 5;
	private static final int DEFAULT_RATING = 3;
	
	private String review;
	private int rating;
	
	public ReviewRating(String review, int rating) {
		this.review = review;
		
		if (rating >= MIN_RATING && rating <= MAX_RATING)
			this.rating = rating;
		else
			this.rating = DEFAULT_RATING;
	} 
	
	public String getReview() {
		return review;
	}
	
	public int getRating() {
		return rating;
	}
}
