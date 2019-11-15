package controller;

import java.util.List;

import model.DataManager;
import model.Movie;
import view.MenuView;
import view.MovieEditView;
import view.MovieView;

public class MovieEditController implements Controller {

	@Override
	public void start() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"View movie details",
				"Add a Movie",
				"Update a Movie",
				"Remove a Movie",
				"Exit"
			);
			
			switch (option) {
				case 1: 
					Movie movie = MenuView.getLabelledItem("Select a movie", movieList);
					MovieView.printMovieDetails(movie);
					break;
					
				case 2:
					MovieEditView.addMovie();
					break;
					
				case 3:
					MovieEditView.updateMovie();	
					break;
					
				case 4:
					MovieEditView.removeMovie();
					break;
					
				case 5:
					NavigationController.goBack();
					return;
			}
		}
	}
}
