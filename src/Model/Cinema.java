package Model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema implements Serializable{
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
	
	public void createShowTime(boolean[][] layout, LocalDateTime startTime,  Movie movie) {
		ShowTime showTime = new ShowTime(layout, startTime,  movie);
		this.showTimes.add(showTime);
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
}


