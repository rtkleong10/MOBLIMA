package Controller;

import java.util.HashMap;

import Model.*;

public class CineplexManager extends DataManager {
	private static final String CINEMASTAFF_FILENAME = "res/data/cinemaStaff.dat";
	private static final String MOVIEGOER_FILENAME = "res/data/movieGoer.dat";
	private static final String PRICINGSCHEME_FILENAME = "res/data/pricingScheme.dat";

	// String key is the username used to access the account
	private static HashMap<String, CinemaStaff> cinemaStaffList;
	private static HashMap<String, MovieGoer> movieGoerList;
	
	private static PricingScheme pricingScheme;
	
	private CineplexManager() {}

	public static void initialize() {
		readCinemaStaffList();
		readMovieGoerList();
		readPricingScheme();
	}
	
	public static void update() {
		updateCinemaStaffList();
		updateMovieGoerList();
		updatePricingScheme();
	}

	public static HashMap<String, CinemaStaff> getCinemaStaffList() {
		return cinemaStaffList;
	}

	public static HashMap<String, MovieGoer> getMovieGoerList() {
		return movieGoerList;
	}

	public static PricingScheme getPricingScheme() {
		return pricingScheme;
	}

	public static void setCinemaStaffList(HashMap<String, CinemaStaff> cinemaStaffList) {
		CineplexManager.cinemaStaffList = cinemaStaffList;
	}

	public static void setMovieGoerList(HashMap<String, MovieGoer> movieGoerList) {
		CineplexManager.movieGoerList = movieGoerList;
	}

	public static void setPricingScheme(PricingScheme pricingScheme) {
		CineplexManager.pricingScheme = pricingScheme;
	}

	@SuppressWarnings("unchecked")
	private static void readCinemaStaffList() {
		Object obj = readSerializedObject(CINEMASTAFF_FILENAME);

		if (obj == null || !(obj instanceof HashMap<?, ?>))
			cinemaStaffList = new HashMap<>();
		else
			cinemaStaffList = (HashMap<String, CinemaStaff>) obj;
	}

	@SuppressWarnings("unchecked")
	private static void readMovieGoerList() {
		Object obj = readSerializedObject(MOVIEGOER_FILENAME);
		
		if (obj == null || !(obj instanceof HashMap<?, ?>))
			movieGoerList = new HashMap<>();
		else
			movieGoerList = (HashMap<String, MovieGoer>) obj;
	}

	private static void readPricingScheme() {
		Object obj = readSerializedObject(PRICINGSCHEME_FILENAME);

		if (obj == null || !(obj instanceof PricingScheme))
			pricingScheme = new PricingScheme();
		else
			pricingScheme = (PricingScheme) obj;
	}

	private static void updateCinemaStaffList() {
		writeSerializedObject(CINEMASTAFF_FILENAME, cinemaStaffList);
	}

	private static void updateMovieGoerList() {
		writeSerializedObject(MOVIEGOER_FILENAME, movieGoerList);
	}

	private static void updatePricingScheme() {
		writeSerializedObject(PRICINGSCHEME_FILENAME, pricingScheme);
	}
}
