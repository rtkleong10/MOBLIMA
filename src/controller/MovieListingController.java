package controller;

import java.util.ArrayList;

import model.DataManager;
import model.Movie;
import view.IOController;
import view.MenuView;
import view.MovieListingView;

public class MovieListingController implements Controller {

	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"Add a Movie",
				"Update a Movie",
				"Remove a Movie",
				"Exit"
			);
			
			
			switch (option) {
				case 1:
					addMovie();
					break;
					
				case 2:
					updateMovie();	
					break;
					
				case 3:
					deleteMovie();
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}
	
	private void addMovie() {
		Movie movie = MovieListingView.addMovie();
		DataManager.getDataStore().getMovieList().add(movie);
	}
	
	private void updateMovie() {
		Movie movie = selectMovie();
		
		if (movie == null)
			return;
		
		int option = MenuView.getMenuOption(
			"What would you want to update",
			"Title",
			"Synopsis",
			"Director",
			"Showing status",
			"Release rating",
			"Movie type",
			"Cast",
			"Duration",
			"Exit"
		);
		
		switch(option) {
		    	
			case 1:
		        movie.setTitle(MovieListingView.readTitle());
		        break;
					  
		    case 2:
		    	movie.setSynopsis(MovieListingView.readSynopsis());
				break;
				
			case 3:
				movie.setDirector(MovieListingView.readDirector());
				break;
				
			case 4:
				movie.setShowingStatus(MovieListingView.readShowingStatus());
				break;
				
			case 5:
				movie.setReleaseRating(MovieListingView.readReleaseRating());
				break;
				
			case 6:
				movie.setMovieType(MovieListingView.readMovieType());
				break;
				
			case 7:
				movie.setCast(MovieListingView.readCast());
				break;
				
			case 8:
				movie.setDuration(MovieListingView.readDuration());
				break;
		}
		
		IOController.displayMessage("Movie updated");
	}
	
	private void deleteMovie() {
		Movie selectedMovie = selectMovie();
		
		if (selectedMovie != null)
			DataManager.getDataStore().getMovieList().remove(selectedMovie);
	}
	
	private Movie selectMovie() {
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
			return movieList.get(option);
		else
			return null;
	}
}
