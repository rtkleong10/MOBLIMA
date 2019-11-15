/**
 *  Represents a cineplex.
 *  MOBLIMA features a total of 3 cineplexes.
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable, LabelledItem {
	private static final long serialVersionUID = 2510448747911179290L;
	private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	private String name;
	/**
	 * Creates Cineplex instance
	 * @param name name of the cineplex
	 */
	public Cineplex(String name) {
		this.name = name;
	}
	/**
	 * Creates Cinema instance and adds it to the array list cinemas
	 * @param cinemaCode starting time of the showtime. Includes the date and time.
	 * @param layout
	 * @param cinemaClass 
	 */
	public void createCinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		Cinema cinema = new Cinema(cinemaCode, layout, cinemaClass);
		this.cinemas.add(cinema);
	}
	
	/**
	 * Gets all the cinemas in the cineplex
	 * @return array list of showtimes
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	
	/**
	 * Gets the name of the cineplex. Function from LabelledItem Interface.
	 * @return this cineplex's name
	 */
	public String getLabel() {
		return name;
	}
	
	/**
	 * Gets the name of the cineplex
	 * @return this cineplex's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the list of showtimes in all cinemas in cineplex
	 * @return array list of all showtimes of this cineplex
	 */
	public ArrayList<ShowTime> getShowTimes() {
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cinema cinema: cinemas)
			showTimes.addAll(cinema.getShowTimes());

		return showTimes;
	}
}
