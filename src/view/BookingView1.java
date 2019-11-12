package view;

import model.*;
import java.util.stream.*;
import controller.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingView1 {
	Scanner sc = new Scanner(System.in);
	private int getInput() {
		int choice = sc.nextInt();
		return choice;
	}
	/**
	 * Displays list of cineplexes for user to select for booking and get user choice of cineplex
	 * @return index of the cineplex user wants to book
	 */
	public int printCineplex(ArrayList <Cineplex> cineplexes) {
		System.out.println("Select Cineplex: ");
		for (int i=0; i<cineplexes.size(); i++) {
			System.out.println(i+1+") " +cineplexes.get(i).getName());
		}
		System.out.print("Option: ");
		return getInput();
	}
	/**
	 * Displays list of movie names and get the movie index that the user wants to book
	 * @param movieNamesmovieNames List of all movies names available in the selected cineplex
	 * @return index of movie that the user wants to book
	 */
	public int getMovieNames(List <String> movieNames) {
		System.out.println("");
		System.out.println("Select Movie:");
		for (int i=0; i<movieNames.size(); i++) {
			System.out.println( i+1 +") "+ movieNames.get(i));
		}
		System.out.print("Option: ");
		return getInput();
		
	}
	/**
	 *  Displays list of showtimes for user to select for booking
	 * @param possibleShow List of all available showtimes for the selected movie 
	 * @return index of showtime that the user wants to book
	 */
	public int getShowTime(List <ShowTime> possibleShow) {
		System.out.println("\nSelect Showtime :");
		for (int i=0; i<possibleShow.size(); i++) {
			Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getStartTime);
			possibleShow.sort(dateComparator);
			System.out.println( i+1 +") "+ possibleShow.get(i).getDate() +"  "+ 
								possibleShow.get(i).getStartTime().toLocalTime());
		}
		System.out.print("Option: ");
		return getInput();
	}
	/**
	 * Displays available seats for user to select for booking
	 * @param showtime selected by user
	 */
	public void displaySeat (ShowTime show) {
		char row= 'A';
		int col =1;
		SeatStatus[][] availSeat = show.getSeatAvailabilities();
		
		for (int i =0; i< (5*(availSeat[0].length)/2)-2; i++)
			System.out.print(" ");
		System.out.println("SCREEN");
		
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		System.out.print("  ");
		
		for (int i =0; i< (availSeat[0].length); i++)
		{
				if(i<10)
					System.out.print("  "+col+"  ");	
				else
					System.out.print(" "+col+"  ");	
			col++;
		}
		System.out.println();	
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		
		System.out.println();
		for (SeatStatus[] i : availSeat)
		{
			System.out.print(row+" " );
			
		   for (SeatStatus j : i)
		   {
		        if (j == SeatStatus.TAKEN) 
		   		System.out.print( "[ x ]");
		   	else if(j== SeatStatus.EMPTY)
		   		System.out.print( "[   ]");
		   	else if(j== SeatStatus.NO_SEAT)
		   		System.out.print( "     ");
		   }
				System.out.print(" "+row );
		   		row++;
		   System.out.println();
		   if(row == availSeat.length) {
			   System.out.print(" ");
		   }
		}
		
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		
		col =1;
		System.out.print("  ");
		
		for (int i =0; i< (availSeat[0].length); i++)
		{
				if(i<10)
					System.out.print("  "+col+"  ");	
				else
					System.out.print(" "+col+"  ");	
			col++;
		}
		
		System.out.println();	
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		for (int i =0; i< (5*(availSeat[0].length)/2)-3; i++)
			System.out.print(" ");
		System.out.println("ENTRANCE");
	}
	/**
	 * Get seats user wants to book
	 * @param show showtime selected by user
	 * @return 2D boolean array; if the seat is selected by user, the value of the seat is true. Otherwise, false.
	 * 
	 */
	public boolean[][] getSeat (ShowTime show){
		boolean[][] layout = show.getLayout();
		boolean[][] selectedSeat = new boolean[layout.length][];
		for(int i=0; i<layout.length; i++) {
			selectedSeat[i] = new boolean[layout[i].length];
			for(int j=0 ; j<layout[i].length; j++) {
				selectedSeat[i][j] = false;
			}
		}
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		System.out.println("Please enter seat no. :");
			String in = sc.nextLine();
			String[] input = in.split("\\s+");
			for (int i=0; i<input.length; i++) {
				row = input[i].charAt(0)-'A';
				col = Integer.parseInt(input[i].substring(1) );
				selectedSeat[row][col-1] = true;
		}	
		return selectedSeat;
	}
	/**
	 * Displays booking information for user to confirm
	 * @param selectedShow showtime selected by user
	 * @param ageGrp number of booking for each age group
	 * @param price price of the booking transaction
	 */
	public void printBookInfo(ShowTime selectedShow, int []ageGrp, double price) {
		System.out.println("   === Booking Information ===");
		ShowTimeView.displayShowTime(Arrays.asList(selectedShow));
		System.out.println("Child: "+ ageGrp[0]);
		System.out.println("Adult: "+ ageGrp[1]);
		System.out.println("Senior Citizen: "+ ageGrp[2]);
		System.out.printf("Price : $%.2f\n",price);
	}
	/**
	 * Get input from user for number of tickets for each age group
	 * @param count
	 * @return 1D integer array of number of tickets for each age group
	 */
	public int[] getAgeGroup(int count) {
		int []ageGrp = new int[3] ;
		Scanner sc = new Scanner( System.in ) ;
		do {
		System.out.println("How many of each age group [Child Adult Senior Citizen]:");
		for(int i=0; i<ageGrp.length; i++)
			ageGrp[i] = sc.nextInt() ;
			
		}while(IntStream.of(ageGrp).sum()!= count);
		
		return ageGrp ;
		}
	
}
