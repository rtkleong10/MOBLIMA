package model;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;

public class Movie implements Serializable {
	private static final long serialVersionUID = 5822630624504041207L;
	
	private String title;
	private String synopsis;
	private String director;
	private String[] cast;
	private ShowingStatus showingStatus;
	private ReleaseRating releaseRating;
	private MovieType movieType;
	private ArrayList<ReviewRating> reviewRatings = new ArrayList<ReviewRating>();
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	private Duration duration;
	
	/**
	 * creates a new Movie with the provided details
	 * it includes title, director, case, rating and 
	 * movie type
	 * @param title Movie title
	 * @param synopsis Movie synopsis
	 * @param director Movie director
	 * @param cast  Movie cast
	 * @param releaseRating Movie rating
	 * @param movieType  Movie type/genre
	 */
	public Movie(String title, String synopsis, String director, String[] cast, ShowingStatus showingStatus, ReleaseRating releaseRating,
			MovieType movieType, Duration duration) {
		this.title = title;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.showingStatus = showingStatus;
		this.releaseRating = releaseRating;
		this.movieType = movieType;
		this.duration = duration;
	}
	
	/**
	 * assigns showtime
	 * @param showTime adds showtime of movie
	 */
	public void addShowTime(ShowTime showTime) {
		this.showTimes.add(showTime);
	}
	
	/**
	 * calculates the overallRating by summing up all the ratings and
	 * dividing the sum by the number of users who rated it 
	 * @return overallRating that is calculated
	 */
	public Double getOverallRating() {
		int noOfReviews = reviewRatings.size();
		
		if (noOfReviews <= 1)
			return null;
		
		Double sum = 0.0;
		for (ReviewRating reviewRating: reviewRatings)
			sum += reviewRating.getRating();
		
		double overallRating = sum / noOfReviews;
		return overallRating;
	}
	
	/**
	 * sums up the totalsales of the movie by through showTime class
	 * @return totalSales of the Movie
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (ShowTime showTime: showTimes)
			totalSales += showTime.getTotalSales();
		
		return totalSales;
	}

	/**
	 * @return title of the Movie
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * @return synopsis of the Movie
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/**
	 * @return director of the Movie
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * @return cast names of the Movie
	 */
	public String[] getCast() {
		return cast;
	}

	/**
	 * @return released rating of the Movie 
	 */
	public ReleaseRating getReleaseRating() {
		return releaseRating;
	}

	/**
	 * @return movie type of the Movie
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/**
	 * @return review ratings of the Movie
	 */
	public ArrayList<ReviewRating> getReviewRatings() {
		return reviewRatings;
	}

	/**
	 * @return showing status of the Movie
	 */
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
}
