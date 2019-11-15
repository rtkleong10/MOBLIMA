package model;

public interface Bookshow {
	public SeatStatus[][] getSeatAvailabilities();
	public int checkAvail( boolean [][] selectedSeat );
	public boolean[][] getLayout()  ;
}
