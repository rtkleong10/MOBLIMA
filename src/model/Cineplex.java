package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cineplex implements Serializable{
	private static final long serialVersionUID = 2510448747911179290L;
	
	private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	private String name;
	
	public Cineplex(String name) {
		this.name = name;
	}
	
	public void createCinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		Cinema cinema = new Cinema(cinemaCode, layout, cinemaClass);
		this.cinemas.add(cinema);
	}
	
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	
	public String getName() {
		return name;
	}
	
	public List <ShowTime> getShowTimes() {
		List <Cinema> cin = this.getCinemas();
		List <ShowTime> allShowTime = new ArrayList <>();
		for (int j =0; j<cin.size(); j++) {
				allShowTime.addAll(cin.get(j).getShowTimes());
		}
		return allShowTime;
	}
}
