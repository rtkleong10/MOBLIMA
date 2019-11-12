package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class DataStore implements Serializable {
	private static final long serialVersionUID = -9165821480078981092L;
	
	private HashMap<String, CinemaStaff> cinemaStaffList = new HashMap<String, CinemaStaff>();
	private HashMap<String, MovieGoer> movieGoerList = new HashMap<String, MovieGoer>();
	
	private PricingScheme pricingScheme = new PricingScheme();
	private ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	public boolean checkCinemaStaffUsername(String username) {
		return cinemaStaffList.containsKey(username);
	}
	
	public CinemaStaff getCinemaStaff(String username, String password) {
		CinemaStaff cinemaStaff = cinemaStaffList.get(username);
		
		if (cinemaStaff != null && cinemaStaff.login(password))
			return cinemaStaff;
		else
			return null;
	}
	
	public boolean addCinemaStaff(CinemaStaff cinemaStaff) {
		String username = cinemaStaff.getUsername();
		
		if (cinemaStaffList.containsKey(username)) {
			return false;
			
		} else {
			cinemaStaffList.put(username, cinemaStaff);
			return true;
		}
	}
	
	public boolean checkMovieGoerUsername(String username) {
		return movieGoerList.containsKey(username);
	}

	public MovieGoer getMovieGoer(String username, String password) {
		MovieGoer movieGoer = movieGoerList.get(username);
		
		if (movieGoer != null && movieGoer.login(password))
			return movieGoer;
		else
			return null;
	}

	public boolean addMovieGoer(MovieGoer movieGoer) {
		String username = movieGoer.getUsername();
		
		if (movieGoerList.containsKey(username)) {
			return false;
			
		} else {
			movieGoerList.put(username, movieGoer);
			return true;
		}
	}
	
	public PricingScheme getPricingScheme() {
		return pricingScheme;
	}
	
	public ArrayList<Cineplex> getCineplexList() {
		return cineplexList;
	}
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	
	public ArrayList<Movie> getBookableMovieList() {
		 ArrayList<Movie> bookableMovieList = new ArrayList<Movie>();
		 
		 for (Movie movie: movieList) {
			 if (movie.getShowingStatus() != ShowingStatus.END_OF_SHOWING)
				 bookableMovieList.add(movie);
		 }
		 
		return bookableMovieList;
	}
}
