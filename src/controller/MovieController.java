package controller;

import java.util.ArrayList;

import model.DataManager;
import model.Movie;
import model.MovieGoer;
import model.ReviewRating;
import view.ListView;
import view.MenuView;
import view.MovieView;

public class MovieController implements Controller {
	MovieGoer movieGoer;
	Movie movie;
	
	public MovieController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		this.movie = selectMovie();
		
		while (true) {
			int option = MenuView.getMenuOption(
				this.movie.getTitle(),
				"View movie details",
				"View past reviews & ratings",
				"Add a review",
				"Exit"
			);
		
			switch (option) {
				case 1:
					MovieView.printMovieDetails(movie);
					break;
					
				case 2:
					ListView.displayList("Past Reviews & Ratings", getReviewRatingStrings(), "No reviews & ratings available");
					break;
					
				case 3:
					MovieView.addMovieReview(movie, movieGoer);
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}
	
	private Movie selectMovie() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList(); 
		Movie[] movieArr = new Movie[movieList.size()];
		movieList.toArray(movieArr);
		return MenuView.getLabelledItem("Select a Movie", movieArr);
	}
	
	private ArrayList<String> getReviewRatingStrings() {
		ArrayList<ReviewRating> reviewRatingsList = movie.getReviewRatings();
			
		ArrayList<String> reviewRatingStrings = new ArrayList<String>();
		for (ReviewRating reviewRating: reviewRatingsList)
			reviewRatingStrings.add(reviewRating.getReview() + " (" + reviewRating.getRating() + "/5) —— " + reviewRating.getMovieGoer().getName());
		
		return reviewRatingStrings;
	}
}
