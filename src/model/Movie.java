package model;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;

/**
 * This class contains all the information of a movie
 */
public class Movie implements Serializable, LabelledItem {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 5822630624504041207L;
	
	/**
	 * The title of the movie
	 */
	private String title;
	/**
	 * The synopsis of the movie
	 */
	private String synopsis;
	/**
	 * The director of the movie
	 */
	private String director;
	/**
	 * The list of cast names of the movie
	 */
	private String[] cast;
	/**
	 * The showing status of the movie
	 */
	private ShowingStatus showingStatus;
	/**
	 * The release rating of the movie
	 */
	private ReleaseRating releaseRating;
	/**
	 * The movie type of the movie
	 */
	private MovieType movieType;
	/**
	 * The list of review ratings of the movie
	 */
	private ArrayList<ReviewRating> reviewRatings = new ArrayList<ReviewRating>();
	/**
	 * The list of show times of the movie
	 */
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	/**
	 * The duration of the movie
	 */
	private Duration duration;
	
	/**
	 * Creates a {@code Movie} object with the given title, synopsis, director, cast, showing status, release rating, movie type
	 * @param title the title of the movie
	 * @param synopsis the synopsis of the movie
	 * @param director the director of the movie
	 * @param cast the cast of the movie
	 * @param showingStatus the showing status of the movie
	 * @param releaseRating the release rating of the movie
	 * @param movieType the movie type of the movie
	 * @param duration the duration of the movie
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
	 * This method adds a showtime to the movie
	 * @param showTime the show time to be added
	 */
	protected void addShowTime(ShowTime showTime) {
		this.showTimes.add(showTime);
	}
	
	/**
	 * This method returns the list of showtimes of a movie
	 * @return the list of showtimes of a movie
	 */
	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}
	
	/**
	 * This method calculates the overallRating by summing up all the ratings and dividing the sum by the number of users who rated it 
	 * @return the calculated overall rating
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
	 * This method sums up the total ticket sales of the movies from all its show times
	 * @return the total ticket sales of the movie
	 */
	public double getTotalSales() {
		double totalSales = 0;
		
		for (ShowTime showTime: showTimes)
			totalSales += showTime.getTotalSales();
		
		return totalSales;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return title;
	}
	
	/**
	 * This method returns the title of the movie
	 * @return the title of the movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method returns the synopsis of the movie
	 * @return the synopsis of the movie
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/**
	 * This method returns the director of the movie
	 * @return the director of the movie
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * This method returns the list of cast names of the movie
	 * @return the list of cast names of the movie
	 */
	public String[] getCast() {
		return cast;
	}

	/**
	 * This method returns the release rating of the movie
	 * @return the release rating of the movie
	 */
	public ReleaseRating getReleaseRating() {
		return releaseRating;
	}

	/**
	 * This method returns the movie type of the movie
	 * @return the movie type of the movie
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/**
	 * This method returns the list of review ratings of the movie
	 * @return the list of review ratings of the movie
	 */
	public ArrayList<ReviewRating> getReviewRatings() {
		return reviewRatings;
	}

	/**
	 * This method returns the showing status of the movie
	 * @return the showing status of the movie
	 */
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	
	/**
	 * This method returns the duration of the movie
	 * @return the duration of the movie
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * This method sets the title for the movie
	 * @param title the new title of the movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method sets the synopsis of the movie
	 * @param synopsis the new synopsis of the movie
	 */
	public void setSynopsis(String synopsis) {  
		this.synopsis = synopsis;
	}

	/**
	 * This method sets director of the movie
	 * @param director the new director of the movie
	 */
	public void setDirector(String director) {  
		this.director = director;
		
	}

	/**
	 * This method sets the list of cast names of the movie
	 * @param cast the new list of cast names of the movie
	 */
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	
	/**
	 * This method sets the release rating of the movie
	 * @param releaseRating the new release rating of the movie
	 */
	public void setReleaseRating(ReleaseRating releaseRating) {
		this.releaseRating = releaseRating;	
	}

	/**
	 * This method sets the movie type of the movie
	 * @param movieType the new movie type of the movie
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	/**
	 * This method sets the duration of the movie
	 * @param duration the new duration of the movie
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	/**
	 * This method sets the showing status of the movie
	 * @param showingStatus the new showing status of the movie
	 */
	public void setShowingStatus(ShowingStatus showingStatus) {
		this.showingStatus = showingStatus;
	}
}
