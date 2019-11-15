package model;

public interface BookShow {
	public SeatStatus[][] getSeatAvailabilities();
	public boolean checkAvail(boolean [][] selectedSeat);
	public boolean[][] getLayout()  ;
}