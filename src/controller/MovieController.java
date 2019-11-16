package controller;

import java.util.List;

import model.DataManager;
import model.Movie;
import model.MovieGoer;
import view.ListView;
import view.MenuView;
import view.MovieView;

/**
 * This class controls the display of the movies and their details and reviews.
 */
public class MovieController implements Controller {
	/**
	 * The movie goer that wants to view the movie
	 */
	private MovieGoer movieGoer;
	/**
	 * The selected movie
	 */
	private Movie movie;
	
	/**
	 * Creates a new {@code MovieController} object for the given movie goer
	 * @param movieGoer the movie goer that wants to view the movie
	 */
	public MovieController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * This method controls the selection of the movie
	 * @return the selected movie
	 */
	private Movie selectMovie() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		return MenuView.getLabelledItem("Select a Movie", movieList);
	}
}
