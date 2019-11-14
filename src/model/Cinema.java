package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema implements Serializable, LabelledItem {
	private static final long serialVersionUID = -3543551736448152076L;
	
	private String cinemaCode;
	private boolean[][] layout;
	private CinemaClass cinemaClass;
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
	
	public Cinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		this.cinemaCode = cinemaCode;
		this.layout = layout;
		this.cinemaClass = cinemaClass;
	}
	
	public void createShowTime(LocalDateTime startDateTime, Movie movie) {
		ShowTime showTime = new ShowTime(this, startDateTime, movie);
		this.showTimes.add(showTime);
		
	}
	
	public String getLabel() {
		return cinemaCode;
	}
	
	public String getCinemaCode() {
		return cinemaCode;
	}
	
	public boolean[][] getLayout() {
		return layout;
	}
	
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}
	
	public ArrayList<ShowTime> getShowTimes() {
		return this.showTimes;
	}
	public void removeShowTime(ShowTime s) {
		showTimes.remove(s);
	}
}


