package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains all the data of the application
 */
public class DataStore implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = -9165821480078981092L;
	
	/**
	 * The cinema staff list with the username of the cinema staff as the key
	 */
	private HashMap<String, CinemaStaff> cinemaStaffList = new HashMap<String, CinemaStaff>();
	/**
	 * The movie goer list with the username of the movie goer as the key
	 */
	private HashMap<String, MovieGoer> movieGoerList = new HashMap<String, MovieGoer>();
	
	/**
	 * The pricing scheme
	 */
	private PricingScheme pricingScheme = new PricingScheme();
	/**
	 * The list of cineplexes
	 */
	private ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
	/**
	 * The list of movies
	 */
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	/**
	 * This methods checks whether the data store contains the given cinema staff username
	 * @param username the cinema staff username to check
	 * @return true if the username is in the data store, otherwise false
	 */
	public boolean checkCinemaStaffUsername(String username) {
		return cinemaStaffList.containsKey(username);
	}
	
	/**
	 * This method returns the {@code CinemaStaff} object corresponding to the username if the password is correct
	 * @param username the username of the cinema staff
	 * @param password the password of the cinema staff
	 * @return the {@code CinemaStaff} object. Returns null if the username or password is incorrect
	 */
	public CinemaStaff getCinemaStaff(String username, String password) {
		CinemaStaff cinemaStaff = cinemaStaffList.get(username);
		
		if (cinemaStaff != null && cinemaStaff.login(password))
			return cinemaStaff;
		else
			return null;
	}
	
	/**
	 * This methods adds a new cinema staff to the data store
	 * @param cinemaStaff the cinema staff to be added
	 * @return true if the cinema staff was successfully added, otherwise false
	 */
	public boolean addCinemaStaff(CinemaStaff cinemaStaff) {
		String username = cinemaStaff.getUsername();
		
		if (cinemaStaffList.containsKey(username)) {
			return false;
			
		} else {
			cinemaStaffList.put(username, cinemaStaff);
			return true;
		}
	}
	
	/**
	 * This methods checks whether the data store contains the given movie goer username
	 * @param username the movie goer username to check
	 * @return true if the username is in the data store, otherwise false
	 */
	public boolean checkMovieGoerUsername(String username) {
		return movieGoerList.containsKey(username);
	}

	/**
	 * This method returns the {@code MovieGoer} object corresponding to the username if the password is correct
	 * @param username the username of the movie goer
	 * @param password the password of the movie goer
	 * @return the {@code MovieGoer} object. Returns null if the username or password is incorrect
	 */
	public MovieGoer getMovieGoer(String username, String password) {
		MovieGoer movieGoer = movieGoerList.get(username);
		
		if (movieGoer != null && movieGoer.login(password))
			return movieGoer;
		else
			return null;
	}

	/**
	 * This methods adds a new movie goer to the data store
	 * @param movieGoer the movie goer to be added
	 * @return true if the movie goer was successfully added, otherwise false
	 */
	public boolean addMovieGoer(MovieGoer movieGoer) {
		String username = movieGoer.getUsername();
		
		if (movieGoerList.containsKey(username)) {
			return false;
			
		} else {
			movieGoerList.put(username, movieGoer);
			return true;
		}
	}
	
	/**
	 * This method returns the pricing scheme
	 * @return the pricing scheme
	 */
	public PricingScheme getPricingScheme() {
		return pricingScheme;
	}
	
	/**
	 * This method returns the cineplex list
	 * @return the cineplex list
	 */
	public ArrayList<Cineplex> getCineplexList() {
		return cineplexList;
	}
	
	/**
	 * This method returns the movie list
	 * @return the movie list
	 */
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
}
