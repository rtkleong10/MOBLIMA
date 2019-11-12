package controller;

import java.util.ArrayList;

import model.DataManager;
import model.Movie;
import model.MovieGoer;
import model.ReviewRating;
import view.ListView;
import view.MenuView;
import view.MovieView;

public class MovieListController implements Controller {
	MovieGoer movieGoer;
	Movie movie;
	
	public MovieListController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		selectMovie();
		
		if (this.movie == null) {
			NavigationController.goBack();
			return;
		}
		
		while (true) {
			int option = MenuView.getMenuOption(
				this.movie.getTitle(),
				"View movie details",
				"View past reviews",
				"Add a review",
				"Exit"
			);
		
			switch (option) {
				case 1:
					MovieView.printMovieDetails(movie);
					break;
					
				case 2:
					ListView.displayList("Past Reviews & Reviewer's Ratings", getReviewRatings(), "No reviews available");
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
	
	private void selectMovie() {
		ArrayList<Movie> movieList = DataManager.getDataStore().getMovieList();
		int size = movieList.size();
		String[] movieStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			movieStrings[i] = movieList.get(i).getTitle();
		}
		
		movieStrings[size] = "Exit";
		
		int option = MenuView.getMenuOption(
			"Select a movie",
			movieStrings
		);
		
		option--;
		
		if (option != size)
			this.movie = movieList.get(option);
	}
	
	private ArrayList<String> getReviewRatings() {
		ArrayList<ReviewRating> reviewRatingsList = movie.getReviewRatings();
			
		ArrayList<String> reviewRatingsString = new ArrayList<String>();
		for (ReviewRating reviewRating: reviewRatingsList)
			reviewRatingsString.add(reviewRating.getReview() + " (" + reviewRating.getRating() + "/5) —— " + reviewRating.getMovieGoer().getName());
		
		return reviewRatingsString;
	}
}
