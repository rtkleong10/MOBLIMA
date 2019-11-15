/**
 *  Represents a cinema screen in a cineplex.
 *  A cineplex can have 3 movie screens.
 */
package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema implements Serializable, LabelledItem {
	private static final long serialVersionUID = -3543551736448152076L;
	private String cinemaCode;
	private boolean[][] layout;
	private CinemaClass cinemaClass;
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	/**
	 * Creates Cinema instance
	 * @param cinemaCode code given to the cinema to uniquely identify it
	 * @param layout seating layout of the cinema
	 * @param cinemaClass class the cinema belongs to (normal or platinum movie suite)
	 */
	public Cinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		this.cinemaCode = cinemaCode;
		this.layout = layout;
		this.cinemaClass = cinemaClass;
	}
	/**
	 * Creates ShowTime instance and adds it to the array list showTimes
	 * @param startTime starting time of the showtime. Includes the date and time.
	 * @param movie instance of Movie specifying movie details. Includes title, synopsis, director,
	 * cast, showing status, age ratings, movie type, reviews, showtimes, and duration.
	 */
	public void createShowTime(LocalDateTime startDateTime, Movie movie) {
		ShowTime showTime = new ShowTime(this, startDateTime, movie);
		this.showTimes.add(showTime);
		
	}
	
	public String getLabel() {
		return cinemaCode + " (" + cinemaClass.getLabel() + ")";
	}
	
	/**
	 * Gets the cinema code for the cinema
	 * @return this cinema's cinemaCode
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}
	/**
	 * Gets the seating layout for the cinema
	 * @return 2D array of this cinema's layout
	 */
	public boolean[][] getLayout() {
		return layout;
	}
	/**
	 * Gets the class for the cinema
	 * @return enum of cinema's cinemaClass
	 */
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}
	/**
	 * Gets all the showtimes for the cinema
	 * @return array list of showtimes
	 */
	public ArrayList<ShowTime> getShowTimes() {
		return this.showTimes;
	}
	/**
	 * Removes a particular showtime from the list of showtimes of the cinema
	 * @param s ShowTime to be deleted from list
	 */
	public void removeShowTime(ShowTime s) {
		showTimes.remove(s);
	}
}


