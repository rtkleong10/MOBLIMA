package Controller;

import java.util.ArrayList;

import Model.*;

public class CineplexManager extends DataManager {
	private static final String CINEMASTAFF_FILENAME = "res/data/cinemaStaff.dat";
	private static final String MOVIEGOER_FILENAME = "res/data/movieGoer.dat";
	private static final String PRICINGSCHEME_FILENAME = "res/data/pricingScheme.dat";
	
    private static ArrayList<CinemaStaff> cinemaStaffList;
    private static ArrayList<MovieGoer> movieGoerList;
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

	public static ArrayList<CinemaStaff> getCinemaStaffList() {
		return cinemaStaffList;
	}

	public static ArrayList<MovieGoer> getMovieGoerList() {
		return movieGoerList;
	}

	public static PricingScheme getPricingScheme() {
		return pricingScheme;
	}
	
	public static void setCinemaStaffList(ArrayList<CinemaStaff> cinemaStaffList) {
		CineplexManager.cinemaStaffList = cinemaStaffList;
		updateCinemaStaffList();
	}

	public static void setMovieGoerList(ArrayList<MovieGoer> movieGoerList) {
		CineplexManager.movieGoerList = movieGoerList;
		updateMovieGoerList();
	}

	public static void setPricingScheme(PricingScheme pricingScheme) {
		CineplexManager.pricingScheme = pricingScheme;
		updatePricingScheme();
	}

	@SuppressWarnings("unchecked")
	private static void readCinemaStaffList() {
		Object obj = readSerializedObject(CINEMASTAFF_FILENAME);
		
		
	    if (obj == null || !(obj instanceof ArrayList<?>))
	    	cinemaStaffList = new ArrayList<CinemaStaff>();
	    else
	    	cinemaStaffList = (ArrayList<CinemaStaff>) obj;
	}
    
    @SuppressWarnings("unchecked")
    private static void readMovieGoerList() {
		Object obj = readSerializedObject(MOVIEGOER_FILENAME);
		
	    if (obj == null || !(obj instanceof ArrayList<?>))
	    	movieGoerList = new ArrayList<MovieGoer>();
	    else
	    	movieGoerList = (ArrayList<MovieGoer>) obj;
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
