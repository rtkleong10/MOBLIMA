package view;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Scanner;

import controller.DataManager;
import model.*;

public class BookingView {
	Scanner sc = new Scanner(System.in);
	private MovieGoer movieGoer;
	private Cinema cinema;
	private ShowTime showTime;
	
	public BookingView(MovieGoer movieGoer, ShowTime showTime) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		displaySeats();
		
		System.out.println("No. of tickets: ");
		int n = sc.nextInt();
		
		boolean[][] selectedSeats = selectSeats(n);
		double totalPrice = calculatePrice(n);
		
		showTime.createBooking("123456", movieGoer, selectedSeats, totalPrice);
		displaySeats();
	}
	
	private boolean[][] selectSeats(int n) {
		boolean[][] layout = showTime.getLayout();
		boolean[][] selectedSeat = new boolean[layout.length][];
		
		// Create all 2D boolean array of false with the same dimensions as layout
		for (int i = 0; i < layout.length; i++) {
			selectedSeat[i] = new boolean[layout[i].length];
			
			for (int j = 0; j < layout[i].length; j++)
				selectedSeat[i][j] = false;
		}
		
		for (int i = 0; i < n; i++) {
			System.out.print("Seat no. (e.g. A1): ");
			String seat = sc.next();
			int row = seat.charAt(0) - 'A';
			int col = Integer.parseInt(seat.substring(1));
			selectedSeat[row][col] = true;
		}
		
		return selectedSeat;
	}
	
	private double calculatePrice(int n) {
		PricingScheme pricingScheme = DataManager.getDataStore().getPricingScheme();
		LocalDate date = showTime.getStartTime().toLocalDate();
		CinemaClass cinemaClass = cinema.getCinemaClass();
		MovieType movieType = showTime.getMovie().getMovieType();
		
		System.out.println("How many of each age group?");
		
		EnumMap<AgeGroup, Integer> ageGroupCount = new EnumMap<AgeGroup, Integer>(AgeGroup.class);
		
		while (true) {
			int totalCount = 0;
			
			for (AgeGroup ageGroup : AgeGroup.values()) { 
			    System.out.print(ageGroup + ": ");
			    int count = sc.nextInt();
			    totalCount += count;
			    ageGroupCount.put(ageGroup, count);
			}
			
			if (totalCount == n)
				break;
			else
				System.out.println("Error! Total doesn't add up to " + n);
		}
		
		double totalPrice = 0;
		
		for (AgeGroup ageGroup : AgeGroup.values()) { 
			totalPrice += ageGroupCount.get(ageGroup) * pricingScheme.getPrice(date, cinemaClass, ageGroup, movieType);
		}
		
		return totalPrice;
	}


	public void displaySeats() {
		char row = 'A';
		int col = 1;
		
		SeatStatus[][] availSeat = showTime.getSeatAvailabilities();
		
		for (int i = 0; i < (3 * (availSeat[0].length) / 2) - 2; i++)
			System.out.print(" ");
		System.out.println("SCREEN");
		
		for (int i = 0; i< 3 * (availSeat[0].length) + 2; i++)
			System.out.print("-");
		System.out.println("");
		
		System.out.print(" ");
		for (int i =0; i< (availSeat[0].length); i++) {
			if(availSeat[0][i] != SeatStatus.NO_SEAT) {
				System.out.print(" "+col+" ");	
				col++;
			}
			else if(availSeat[0][i] == SeatStatus.NO_SEAT) {
				System.out.print("   ");
			}
		}
		System.out.println();	
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		
		System.out.println();
		for (SeatStatus[] i : availSeat)
		{
			System.out.print(row );
			
		   for (SeatStatus j : i)
		   {
		        if (j == SeatStatus.TAKEN) 
		   		System.out.print( "[x]");
		   	else if(j== SeatStatus.EMPTY)
		   		System.out.print( "[ ]");
		   	else if(j== SeatStatus.NO_SEAT)
		   		System.out.print( "   ");
		   }
		   System.out.print(row);
		  row++;
		   System.out.println();
		   if(row == availSeat.length) {
			   System.out.print(" ");
			   //for(int k =0; k<=availSeat[0].length; k++)
		   }
		}
		
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		System.out.println();
		
		col =1;
		System.out.print(" ");
		for (int i =0; i< (availSeat[0].length); i++)
		{
			if(availSeat[0][i] != SeatStatus.NO_SEAT) {
				System.out.print(" "+col+" ");	
				col++;
			}
			else if(availSeat[0][i] == SeatStatus.NO_SEAT) {
				System.out.print("   ");
			}
		}
		System.out.println();	
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		System.out.println();
		for (int i =0; i< (3*(availSeat[0].length)/2)-3; i++)
			System.out.print(" ");
		System.out.println("ENTRANCE");	
	}
}
