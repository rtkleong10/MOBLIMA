package model;

/**
 * This is the interface for booking shows
 */
public interface BookShow {
	/**
	 * This method gets all seat statuses for the showtime
	 * @return 2D array of SeatStatus
	 */
	public SeatStatus[][] getSeatAvailabilities();
	
	/**
	 * This method checks the availability of the seats selected by user
	 * @param selectedSeat a 2D boolean array indicating the seats selected
	 * @return true if all seats are available, otherwise false
	 */
	public boolean checkAvail(boolean [][] selectedSeat);
	
	/**
	 * This method returns the layout of the cinema of the show time
	 * @return the layout of the cinema of the showtime
	 */
	public boolean[][] getLayout();
}