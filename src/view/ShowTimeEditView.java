package view;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import model.Cinema;
import model.Cineplex;
import model.DataManager;
import model.Movie;
import model.ShowTime;

/**
 * This class displays the form for creating, modifying and removing show times
 */
public class ShowTimeEditView {
	/**
	 * This method displays the form for creating a show time
	 */
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
	
	/**
	 * This method displays the form for modifying a show time
	 */
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
	
	/**
	 * This method displays the form for removing a show time
	 */
	public static void removeShowTime() {
		Cinema cinema = readCinema();
		List<ShowTime> showTimeList = cinema.getShowTimes();
		ShowTime showTime = MenuView.getLabelledItem("Select a show time", showTimeList);
		showTime.remove();
	}
	
	/**
	 * This methods reads in the new cinema
	 * @return the new cinema
	 */
	private static Cinema readCinema() {
		// Select a cineplex
		List<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		Cineplex cineplex = MenuView.getLabelledItem("Select a cineplex", cineplexList);
		
		// Select a cinema
		List<Cinema> cinemaList = cineplex.getCinemas();
		return MenuView.getLabelledItem("Select a cinema", cinemaList);
	}
	
	/**
	 * This methods reads in the new movie
	 * @return the new movie
	 */
	private static Movie readMovie() {
		// Select a movie
		List<Movie> movieList = DataManager.getDataStore().getMovieList();
		return MenuView.getLabelledItem("Select a movie", movieList);
	}
	
	/**
	 * This methods reads in the new start date and time
	 * @return the new start date and time
	 */
	private static LocalDateTime readStartDateTime() {
		return IOController.readDateTime("Enter start date & time (dd/mm/yyyy hh:mm): ");
	}
	
	/**
	 * This method checks whether there is a clash for the selected start date and time and duration and the existing show times in the cinema
	 * @param startDateTime the selected start date and time
	 * @param duration the selected duration
	 * @param cinema the selected cinema
	 * @return if there's a clash, it will return true, otherwise it returns false
	 */
	public static boolean checkClash(LocalDateTime startDateTime, Duration duration, Cinema cinema) {
		return checkClash(startDateTime, duration, cinema, null);
	}
	
	/**
	 * This method checks whether there is a clash for the selected start date and time and duration and the existing show times in the cinema, beside the current show time
	 * @param startDateTime the selected start date and time
	 * @param duration the selected duration
	 * @param cinema the selected cinema
	 * @param currentShowTime the current show time
	 * @return if there's a clash, it will return true, otherwise it returns false
	 */
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
