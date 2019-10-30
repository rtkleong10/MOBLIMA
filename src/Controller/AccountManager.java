package Controller;

import java.util.HashMap;

import Model.CinemaStaff;
import Model.MovieGoer;

public class AccountManager extends DataManager {
	private static final String CINEMASTAFF_FILENAME = "res/data/cinemaStaff.dat";
	private static final String MOVIEGOER_FILENAME = "res/data/movieGoer.dat";
	
	// String key is the username used to access a specific account
	private static HashMap<String, CinemaStaff> cinemaStaffList;
	private static HashMap<String, MovieGoer> movieGoerList;
	
	private AccountManager() {}
	
	public static void initialise() {
		readCinemaStaffList();
		readMovieGoerList();
	}
	
	public static void update() {
		updateCinemaStaffList();
		updateMovieGoerList();
	}
	
	public static CinemaStaff getCinemaStaff(String username) {
		return cinemaStaffList.get(username);
	}
	
	public static boolean addCinemaStaff(CinemaStaff cinemaStaff) {
		String username = cinemaStaff.getUsername();
		
		if (cinemaStaffList.containsKey(username)) {
			return false;
			
		} else {
			cinemaStaffList.put(username, cinemaStaff);
			return true;
		}
	}

	public static MovieGoer getMovieGoer(String username) {
		return movieGoerList.get(username);
	}

	public static boolean addMovieGoer(MovieGoer movieGoer) {
		String username = movieGoer.getUsername();
		
		if (movieGoerList.containsKey(username)) {
			return false;
			
		} else {
			movieGoerList.put(username, movieGoer);
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void readCinemaStaffList() {
		Object obj = readSerializedObject(CINEMASTAFF_FILENAME);

		if (obj == null || !(obj instanceof HashMap<?, ?>))
			cinemaStaffList = new HashMap<String, CinemaStaff>();
		else
			cinemaStaffList = (HashMap<String, CinemaStaff>) obj;
	}

	@SuppressWarnings("unchecked")
	private static void readMovieGoerList() {
		Object obj = readSerializedObject(MOVIEGOER_FILENAME);
		
		if (obj == null || !(obj instanceof HashMap<?, ?>))
			movieGoerList = new HashMap<String, MovieGoer>();
		else
			movieGoerList = (HashMap<String, MovieGoer>) obj;
	}
	
	private static void updateCinemaStaffList() {
		writeSerializedObject(CINEMASTAFF_FILENAME, cinemaStaffList);
	}

	private static void updateMovieGoerList() {
		writeSerializedObject(MOVIEGOER_FILENAME, movieGoerList);
	}
}
