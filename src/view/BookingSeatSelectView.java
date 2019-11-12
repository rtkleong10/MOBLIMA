package view;

import java.util.Arrays;
import java.util.EnumMap;

import controller.BookingController;
import model.*;

public class BookingSeatSelectView extends View {
	private MovieGoer movieGoer;
	private ShowTime showTime;
	
	public BookingSeatSelectView(MovieGoer movieGoer, ShowTime showTime) {
		this.movieGoer = movieGoer;
		this.showTime = showTime;
	}
	
	public void start() {
		displaySeats();
		
		if (showTime.checkFull()) {
			System.out.println("Sorry, this show time is fully booked");
			exit();
			return;
		}
		
		System.out.print("How many seats would you like to book: ");
		int n = IOController.readInt();
		
		boolean[][] selectedSeat = getSeats(n);
		double totalPrice = calculatePrice(n);
		
		boolean confirm = confirmBooking();
		
		if (confirm) {
			String transactionId = BookingController.getTid(showTime.getCinema());
			showTime.createBooking(transactionId, movieGoer, selectedSeat, totalPrice);
			System.out.println("\nBooking Successful");
			System.out.println("Transaction ID: " + transactionId);
			
		} else {
			System.out.println("Booking Cancelled");
		}
		
		displaySeats();
		
		exit();
	}
	
	private boolean confirmBooking() {
		System.out.print("Confirm Booking [y/n]: ");
		char confirm = IOController.readLine().charAt(0);
		return confirm == 'y';
	}
	
	private boolean[][] getSeats(int n) {
		boolean[][] layout = showTime.getLayout();
		boolean[][] selectedSeat = new boolean[layout.length][];
		
		while (true) {
			// Create all 2D boolean array of false with the same dimensions as layout
			for (int i = 0; i < layout.length; i++) {
				selectedSeat[i] = new boolean[layout[i].length];
				
				for (int j = 0; j < layout[i].length; j++)
					selectedSeat[i][j] = false;
			}
			
			System.out.print("Please enter the seat no.s (e.g. A1 A2 A3): ");
			String line = IOController.readLine();
			String[] inputs = line.split("\\s+");
			for (int i = 0; i < n; i++) {
				int row = inputs[i].charAt(0) - 'A';
				int col = Integer.parseInt(inputs[i].substring(1));
				selectedSeat[row][col-1] = true;
			}
			
			if (showTime.checkAvail(selectedSeat))
				break;
			else {
				System.out.println("Unavailable seats selected");
				System.out.println("Select seats again");
			}
		}
		
		return selectedSeat;
	}
	
	private double calculatePrice(int n) {
		System.out.println("How many of each age group?");
		
		EnumMap<AgeGroup, Integer> ageGroupCount = new EnumMap<AgeGroup, Integer>(AgeGroup.class);
		
		while (true) {
			int totalCount = 0;
			
			for (AgeGroup ageGroup: AgeGroup.values()) { 
			    System.out.print(ageGroup + ": ");
			    int count = IOController.readInt();
			    totalCount += count;
			    ageGroupCount.put(ageGroup, count);
			}
			
			if (totalCount == n)
				break;
			else
				System.out.println("Error! Total doesn't add up to " + n);
		}
		
		double totalPrice = BookingController.calculatePrice(showTime, ageGroupCount);
		
		printBookInfo(ageGroupCount, totalPrice);
		
		return totalPrice;
	}


	private void displaySeats() {
		char row= 'A';
		int col =1;
		SeatStatus[][] availSeat = showTime.getSeatAvailabilities();
		
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
	
	private void printBookInfo(EnumMap<AgeGroup, Integer> ageGroupCount, double totalPrice) {
		System.out.println("   === Booking Information ===");
		ShowTimeView.displayShowTime(Arrays.asList(showTime));
		
		for (AgeGroup ageGroup: AgeGroup.values()) 
		    System.out.print(ageGroup + ": " + ageGroupCount.get(ageGroup));
		
		System.out.printf("Price : $%.2f\n", totalPrice);
	}
}
