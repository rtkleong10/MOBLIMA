package controller;

import java.util.List;

import model.DataManager;
import model.Movie;
import model.MovieGoer;
import view.ListView;
import view.MenuView;
import view.MovieView;

public class MovieController implements Controller {
	private MovieGoer movieGoer;
	private Movie movie;
	
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
					ListView.displayLabelledItemList("Past Reviews & Ratings", movie.getReviewRatings(), "No reviews & ratings available");
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
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		return MenuView.getLabelledItem("Select a Movie", movieList);
	}
}
