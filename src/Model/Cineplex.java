package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
	private static final long serialVersionUID = 2510448747911179290L;
	
	private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	
	public void createCinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		Cinema cinema = new Cinema(cinemaCode, layout, cinemaClass);
		this.cinemas.add(cinema);
	}
	
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
}
