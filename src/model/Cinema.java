package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class contains all the information of a cinema
 */
public class Cinema implements Serializable, LabelledItem {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = -3543551736448152076L;
	/**
	 * The cinema code of the cinema to uniquely identify it
	 */
	private String cinemaCode;
	/**
	 * The seating layout of the cinema. The seating layout is represented as a 2D boolean array. If there's a seat at a certain position, it's true, otherwise it's false.
	 */
	private boolean[][] layout;
	/**
	 * The cinema class of the cinema
	 */
	private CinemaClass cinemaClass;
	/**
	 * The showtimes of the cinema
	 */
	private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();

	/**
	 * Creates a {@code Cinema} object for the given cinema code, layout and cinema class
	 * @param cinemaCode the code of the cinema
	 * @param layout the seating layout of the cinema
	 * @param cinemaClass the cinema class of the cinema
	 */
	public Cinema(String cinemaCode, boolean[][] layout, CinemaClass cinemaClass) {
		this.cinemaCode = cinemaCode;
		this.layout = layout;
		this.cinemaClass = cinemaClass;
	}

	/**
	 * Creates {@code ShowTime} object for the given start date time and movie and adds it to the cinema's show times
	 * @param startDateTime the start date and time of the showtime
	 * @param movie the movie of the showtime
	 */
	public void createShowTime(LocalDateTime startDateTime, Movie movie) {
		ShowTime showTime = new ShowTime(this, startDateTime, movie);
		this.showTimes.add(showTime);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return cinemaCode + " (" + cinemaClass.getLabel() + ")";
	}
	
	/**
	 * This method returns the cinem code of the cinema
	 * @return the cinem code of the cinema
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}

	/**
	 * This method returns the seating layout of the cinema
	 * @return the seating layout of the cinema
	 */
	public boolean[][] getLayout() {
		return layout;
	}

	/**
	 * This method returns the cinema class of the cinema
	 * @return the cinema class of the cinema
	 */
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	/**
	 * This method returns the list of showtimes of the cinema
	 * @return the list of showtimes of the cinema
	 */
	public ArrayList<ShowTime> getShowTimes() {
		return this.showTimes;
	}
}


