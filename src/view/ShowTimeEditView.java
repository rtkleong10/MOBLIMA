package view;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import model.Cinema;
import model.Cineplex;
import model.DataManager;
import model.Movie;
import model.ShowTime;

public class ShowTimeEditView {
	public static void addShowTime() {
		Cinema cinema = readCinema();
		Movie movie = readMovie();
		LocalDateTime startDateTime = readStartDateTime();
		
		Duration duration = movie.getDuration();
		
		if (checkClash(startDateTime, duration, cinema))
			IOController.displayMessage("Error: This show time clashes with another show time in the same cinema");
		else
			cinema.createShowTime(startDateTime, movie);
	}
	
	public static void updateShowTime() {
		Cinema cinema = readCinema();
		ShowTime showTime = MenuView.getLabelledItem("Select a show time", cinema.getShowTimes());
		
		int option = MenuView.getMenuOption(
			"What would you want to update",
			"Start date & time",
			"Movie"
		);
		
		switch(option) {
		    	
			case 1:
				LocalDateTime startDateTime = readStartDateTime();
				if (checkClash(startDateTime, showTime.getMovie().getDuration(), cinema, showTime))
					IOController.displayMessage("Error: This show time clashes with another show time in the same cinema");
				else
					showTime.setStartDateTime(startDateTime);
		        break;
					  
		    case 2:
		    	showTime.setMovie(readMovie());
				break;
		}
	}
	
	public static void removeShowTime() {
		Cinema cinema = readCinema();
		List<ShowTime> showTimeList = cinema.getShowTimes();
		ShowTime showTime = MenuView.getLabelledItem("Select a show time", showTimeList);
		showTime.remove();
	}
	
	private static Cinema readCinema() {
		// Select a cineplex
		List<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		Cineplex cineplex = MenuView.getLabelledItem("Select a cineplex", cineplexList);
		
		// Select a cinema
		List<Cinema> cinemaList = cineplex.getCinemas();
		return MenuView.getLabelledItem("Select a cinema", cinemaList);
	}
	
	private static Movie readMovie() {
		// Select a movie
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		return MenuView.getLabelledItem("Select a movie", movieList);
	}
	
	private static LocalDateTime readStartDateTime() {
		return IOController.readDateTime("Enter start date & time (dd/mm/yyyy hh:mm): ");
	}
	
	public static boolean checkClash(LocalDateTime startDateTime, Duration duration, Cinema cinema) {
		return checkClash(startDateTime, duration, cinema, null);
	}
	
	public static boolean checkClash(LocalDateTime startDateTime, Duration duration, Cinema cinema, ShowTime currentShowTime) {
		for (ShowTime showTime: cinema.getShowTimes()) {
			LocalDateTime endDateTime = startDateTime.plus(duration);
			LocalDateTime oldShowTimeStart = showTime.getStartDateTime();
			LocalDateTime oldShowTimeEnd = oldShowTimeStart.plus(showTime.getMovie().getDuration());
			
			boolean isClash = (
				(oldShowTimeStart.isBefore(endDateTime) && !oldShowTimeStart.isEqual(endDateTime)) &&
				(startDateTime.isBefore(oldShowTimeEnd) && !startDateTime.isEqual(oldShowTimeEnd)) &&
				showTime != currentShowTime
			);
			
			if (isClash)
				return true;
		}
		
		return false;
	}
}
