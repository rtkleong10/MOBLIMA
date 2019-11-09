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
	
	public Movie(String title, String synopsis, String director, String[] cast, ReleaseRating releaseRating,
			MovieType movieType, Duration duration) {
		this.title = title;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.releaseRating = releaseRating;
		this.movieType = movieType;
		this.duration = duration;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public void setSynopsis(String synopsis){  
		this.synopsis=synopsis;
		
	}
	
	public void setDirector(String director){  
		this.director=director;
		
	}
	public void setCast(String[] cast) {
		this.cast=cast;
	}
	
	public void setReleaseRating(ReleaseRating releaserating) {
		this.releaseRating= releaserating;	
	}
	
	public void setMovieType(MovieType movietype) {
		this.movieType = movietype;
	}
	public void setDuration(Duration duration) {
		this.duration=duration;
	}
	
	public void addShowTime(ShowTime showTime) {
		this.showTimes.add(showTime);
	}
	public void setShowingStatus(ShowingStatus s)
	{   this.showingStatus =s;
		
		
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
	
	public double getTotalSales() {
		double totalSales = 0;
		
		for (ShowTime showTime: showTimes)
			totalSales += showTime.getTotalSales();
		
		return totalSales;
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

	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
}
