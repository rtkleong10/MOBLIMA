package view;

import java.time.Duration;

import model.Movie;
import model.ReleaseRating;
import model.ShowingStatus;
import model.MovieType;

public class MovieListingView {
	public static Movie addMovie() {
		String title = readTitle();
		String synopsis = readSynopsis();
		String director = readDirector();
		
		ShowingStatus showingStatus = readShowingStatus();
		ReleaseRating releaseRating = readReleaseRating();
		MovieType movieType = readMovieType();
		
		String[] cast = readCast();
		Duration duration = readDuration();
		
		Movie movie = new Movie(title, synopsis, director, cast, showingStatus, releaseRating, movieType, duration);
		
		return movie;
	}
	
	public static String readTitle() {
		return IOController.readLine("Enter title: ");
	}
	
	public static String readSynopsis() {
		return IOController.readLine("Enter movie synopsis: ");
	}
	
	public static String readDirector() {
		return IOController.readLine("Enter name of director: ");
	}
	
	public static ShowingStatus readShowingStatus() {
		return MenuView.getLabelledItem("Select a showing status", ShowingStatus.values());
	}
	
	public static ReleaseRating readReleaseRating() {
		return MenuView.getLabelledItem("Select a release rating", ReleaseRating.values());
	}
	
	public static MovieType readMovieType() {
		return MenuView.getLabelledItem("Select a movie type", MovieType.values());
	}
	
	public static String[] readCast() {
		int castSize = IOController.readInt("Enter the cast size: ");
		
		String[] castNames = new String[castSize];
		IOController.displayMessage("Enter names of all cast members");
		
		for (int i = 0; i < castSize; i++)
			castNames[i] = IOController.readLine();
		
		return castNames;
	}
	
	public static Duration readDuration() {
		int durationHours = IOController.readInt("Enter movie duration: ");
		return Duration.ofHours(durationHours);
	}
}
