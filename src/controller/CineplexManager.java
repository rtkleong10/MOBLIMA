package controller;

import java.util.ArrayList;

import model.Cineplex;
import model.Movie;
import model.PricingScheme;

public class CineplexManager extends DataManager {
	private static final String PRICINGSCHEME_FILENAME = "res/data/pricingScheme.dat";
	private static final String CINEPLEX_FILENAME = "res/data/cineplex.dat";
	private static final String MOVIE_FILENAME = "res/data/movie.dat";
	
	private static PricingScheme pricingScheme;
	private static ArrayList<Cineplex> cineplexList;
	private static ArrayList<Movie> movieList;
	
	private CineplexManager() {}

	public static void initialise() {
		readPricingScheme();
		readCineplexList();
		readMovieList();
	}
	
	public static void update() {
		updatePricingScheme();
		updateCineplexList();
		updateMovieList();
	}
	
	public static PricingScheme getPricingScheme() {
		return pricingScheme;
	}
	
	public static ArrayList<Cineplex> getCineplexList() {
		return cineplexList;
	}
	
	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}

	private static void readPricingScheme() {
		Object obj = readSerializedObject(PRICINGSCHEME_FILENAME);

		if (obj == null || !(obj instanceof PricingScheme))
			pricingScheme = new PricingScheme();
		else
			pricingScheme = (PricingScheme) obj;
	}
	
	@SuppressWarnings("unchecked")
	private static void readCineplexList() {
		Object obj = readSerializedObject(CINEPLEX_FILENAME);
		
		if (obj == null || !(obj instanceof ArrayList<?>))
			cineplexList = new ArrayList<Cineplex>();
		else
			cineplexList = (ArrayList<Cineplex>) obj;
	}
	
	@SuppressWarnings("unchecked")
	private static void readMovieList() {
		Object obj = readSerializedObject(MOVIE_FILENAME);
		
		if (obj == null || !(obj instanceof ArrayList<?>))
			movieList = new ArrayList<Movie>();
		else
			movieList = (ArrayList<Movie>) obj;
	}
	
	private static void updatePricingScheme() {
		writeSerializedObject(PRICINGSCHEME_FILENAME, pricingScheme);
	}
	
	private static void updateCineplexList() {
		writeSerializedObject(PRICINGSCHEME_FILENAME, cineplexList);
	}
	
	private static void updateMovieList() {
		writeSerializedObject(PRICINGSCHEME_FILENAME, movieList);
	}
}
