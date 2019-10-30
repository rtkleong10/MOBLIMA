package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
	private static final long serialVersionUID = 5822630624504041207L;
	
	private String title;
	private String synopsis;
	private String director;
	private String[] cast;
	private ReleaseRating releaseRating;
	private MovieType movieType;
	private ArrayList<ReviewRating> reviewRatings = new ArrayList<ReviewRating>();
	
	public Movie(String title, String synopsis, String director, String[] cast, ReleaseRating releaseRating,
			MovieType movieType) {
		this.title = title;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.releaseRating = releaseRating;
		this.movieType = movieType;
	}
	
	public Double getOverallRating() {
		int noOfReviews = reviewRatings.size();
		
		if (noOfReviews <= 1)
			return null;
		
		int sum = 0;
		for (ReviewRating reviewRating: reviewRatings)
			sum += reviewRating.getRating();
		
		double overallRating = sum / noOfReviews;
		return overallRating;
	}

	public String getTitle() {
		return title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getDirector() {
		return director;
	}

	public String[] getCast() {
		return cast;
	}

	public ReleaseRating getReleaseRating() {
		return releaseRating;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public ArrayList<ReviewRating> getReviewRatings() {
		return reviewRatings;
	}
}
