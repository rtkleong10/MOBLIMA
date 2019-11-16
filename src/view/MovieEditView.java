package view;

import java.time.Duration;
import java.util.List;

import model.DataManager;
import model.Movie;
import model.ReleaseRating;
import model.ShowingStatus;
import model.MovieType;

/**
 * This class displays the form for creating, modifying and removing movies
 */
public class MovieEditView {
	/**
	 * This method displays the form for creating a movie
	 */
	public static void addMovie() {
		String title = readTitle();
		String synopsis = readSynopsis();
		String director = readDirector();
		
		ShowingStatus showingStatus = readShowingStatus();
		ReleaseRating releaseRating = readReleaseRating();
		MovieType movieType = readMovieType();
		
		String[] cast = readCast();
		Duration duration = readDuration();
		
		Movie movie = new Movie(title, synopsis, director, cast, showingStatus, releaseRating, movieType, duration);
		DataManager.getDataStore().getMovieList().add(movie);
	}
	
	/**
	 * This method displays the form for modifying a movie
	 */
	public static void updateMovie() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		Movie movie = MenuView.getLabelledItem("Select a movie", movieList); 
		
		int option = MenuView.getMenuOption(
			"What would you want to update",
			"Title",
			"Synopsis",
			"Director",
			"Showing status",
			"Release rating",
			"Movie type",
			"Cast",
			"Duration"
		);
		
		switch(option) {
		    	
			case 1:
		        movie.setTitle(MovieEditView.readTitle());
		        break;
					  
		    case 2:
		    	movie.setSynopsis(MovieEditView.readSynopsis());
				break;
				
			case 3:
				movie.setDirector(MovieEditView.readDirector());
				break;
				
			case 4:
				movie.setShowingStatus(MovieEditView.readShowingStatus());
				break;
				
			case 5:
				movie.setReleaseRating(MovieEditView.readReleaseRating());
				break;
				
			case 6:
				movie.setMovieType(MovieEditView.readMovieType());
				break;
				
			case 7:
				movie.setCast(MovieEditView.readCast());
				break;
				
			case 8:
				movie.setDuration(MovieEditView.readDuration());
				break;
		}
	}
	
	/**
	 * This method displays the form for removing a movie
	 */
	public static void removeMovie() {
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		Movie movie = MenuView.getLabelledItem("Select a movie", movieList);
		movie.setShowingStatus(ShowingStatus.END_OF_SHOWING);
	}
	
	/**
	 * This methods reads in the new title
	 * @return the new title
	 */
	private static String readTitle() {
		return IOController.readLine("Title: ");
	}
	
	/**
	 * This methods reads in the new synopsis
	 * @return the new synopsis
	 */
	private static String readSynopsis() {
		return IOController.readLine("Synopsis: ");
	}
	
	/**
	 * This methods reads in the new director
	 * @return the new director
	 */
	private static String readDirector() {
		return IOController.readLine("Director: ");
	}
	
	/**
	 * This methods reads in the new showing status
	 * @return the new showing status
	 */
	private static ShowingStatus readShowingStatus() {
		return MenuView.getLabelledItem("Showing status: ", ShowingStatus.values());
	}
	
	/**
	 * This methods reads in the new release rating
	 * @return the new release rating
	 */
	private static ReleaseRating readReleaseRating() {
		return MenuView.getLabelledItem("Release rating: ", ReleaseRating.values());
	}
	
	/**
	 * This methods reads in the new movie type
	 * @return the new movie type
	 */
	private static MovieType readMovieType() {
		return MenuView.getLabelledItem("Movie type: ", MovieType.values());
	}
	
	/**
	 * This methods reads in the new cast
	 * @return the new cast
	 */
	private static String[] readCast() {
		int castSize = IOController.readInt("Enter the cast size: ");
		
		String[] castNames = new String[castSize];
		IOController.displayMessage("Cast names: ");
		
		for (int i = 0; i < castSize; i++)
			castNames[i] = IOController.readLine("• ");
		
		return castNames;
	}
	
	/**
	 * This methods reads in the new duration
	 * @return the new duration
	 */
	private static Duration readDuration() {
		return IOController.readDuration("Duration (in minutes): ");
	}
}
