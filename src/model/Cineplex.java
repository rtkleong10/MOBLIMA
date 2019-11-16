package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the information of a cineplex
 */
public class Cineplex implements Serializable, LabelledItem {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 2510448747911179290L;
	/**
	 * The list of cinemas in the cineplex
	 */
	private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	/**
	 * The name of the cineplex
	 */
	private String name;
	/**
	 * Creates a {@code Cineplex} with the given name
	 * @param name the name of the cineplex
	 */
	public Cineplex(String name) {
		this.name = name;
	}

	/**
	 * Creates cinema and adds it to the cineplex's list of cinemas
	 * @param cinemaCode the cinema code of the cinema
	 * @param layout the seating layout of the cinema
	 * @param cinemaClass the cinema class of the cinema
	 */
	public void createCinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		Cinema cinema = new Cinema(cinemaCode, layout, cinemaClass);
		this.cinemas.add(cinema);
	}
	
	/**
	 * This method return the list of cinemas in the cineplex
	 * @return the list of cinemas in the cineplex
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return name;
	}
	
	/**
	 * This method returns the name of the cineplex
	 * @return the name of the cineplex
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns the list of showtimes in all the cinemas in cineplex
	 * @return the list of showtimes in all the cinemas in cineplex
	 */
	public ArrayList<ShowTime> getShowTimes() {
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cinema cinema: cinemas)
			showTimes.addAll(cinema.getShowTimes());

		return showTimes;
	}
}
