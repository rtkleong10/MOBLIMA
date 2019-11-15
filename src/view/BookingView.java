package view;

import java.util.EnumMap;

import model.AgeGroup;
import model.SeatStatus;
import model.BookShow;

public class BookingView {
	
	/**
	 * Get seats user wants to book
	 * @param show showtime selected by user
	 * @return 2D boolean array; if the seat is selected by user, the value of the seat is true. Otherwise, false.
	 * 
	 */
	public static boolean[][] getSeats(int n, BookShow showTime) {
		boolean[][] layout = showTime.getLayout();
		boolean[][] selectedSeat = new boolean[layout.length][];
		
		while (true) {
			// Create all 2D boolean array of false with the same dimensions as layout
			for (int i = 0; i < layout.length; i++) {
				selectedSeat[i] = new boolean[layout[i].length];
				
				for (int j = 0; j < layout[i].length; j++)
					selectedSeat[i][j] = false;
			}
			
			IOController.displayMessage("Enter the seat no.s (e.g. A1): ");
			
			for (int i = 0; i < n; i++) {
				String input = IOController.readLine("");
				int row = input.charAt(0) - 'A';
				int col = Integer.parseInt(input.substring(1));
				selectedSeat[row][col - 1] = true;
			}
			
			if (showTime.checkAvail(selectedSeat)) { 
				break;
				
			} else {
				IOController.displayMessage("Unavailable seats selected");
				IOController.displayMessage("Select seats again");
			}
		}
		
		return selectedSeat;
	}
	
	public static EnumMap<AgeGroup, Integer> getAgeGroupCount(int n) {
		IOController.displayMessage("How many of each age group?");
		
		EnumMap<AgeGroup, Integer> ageGroupCount = new EnumMap<AgeGroup, Integer>(AgeGroup.class);
		
		while (true) {
			int totalCount = 0;
			
			for (AgeGroup ageGroup: AgeGroup.values()) { 
			    int count = IOController.readInt(ageGroup.getLabel() + ": ");
			    totalCount += count;
			    ageGroupCount.put(ageGroup, count);
			}
			
			if (totalCount == n)
				break;
			else
				IOController.displayMessage("Error! Total doesn't add up to " + n);
		}
		
		return ageGroupCount;
	}

	/**
	 * Displays available seats for user to select for booking
	 * @param showtime selected by user
	 */
	public static void displaySeats(BookShow showTime) {
		SeatStatus[][] availSeats = showTime.getSeatAvailabilities();
		int textWidth = availSeats[0].length * 5 + 4;
		
		// Create Line String
		String line = "";
		for (int i =0; i< textWidth; i++)
			line += "-";
		
		// Create column headers
		String columnHeaders = "  ";
		
		for (int i = 0; i < availSeats[0].length; i++) {
			if (i < 10)
				columnHeaders += "  " + (i + 1) + "  ";	
			else
				columnHeaders += " " + (i + 1) + "  ";	
		}
		
		IOController.displayMessageCentred("SCREEN", textWidth);
		
		IOController.displayMessage(line);
		IOController.displayMessage(columnHeaders);
		IOController.displayMessage(line);
		
		// Print rows of seats
		char row = 'A';
		
		for (SeatStatus[] availSeatRow: availSeats) {
			String rowString = "";
			rowString += row + " ";
			
			for (SeatStatus seatStatus: availSeatRow) {
				switch (seatStatus) {
					case TAKEN:
						rowString += "[ x ]";
						break;
						
					case EMPTY:
						rowString += "[   ]";
						break;
						
					case NO_SEAT:
						rowString += "     ";
						break;
				}
			}
			rowString += " " + row;
			IOController.displayMessage(rowString);
		   	row++;
		}
		
		IOController.displayMessage(line);
		IOController.displayMessage(columnHeaders);
		IOController.displayMessage(line);
		
		IOController.displayMessageCentred("ENTRANCE", textWidth);
		
		IOController.pressEnterToContinue();
	}
	
	public static void printBookInfo(BookShow showTime, EnumMap<AgeGroup, Integer> ageGroupCount, double totalPrice) {
		IOController.displayTitle("Booking Information");
		for (AgeGroup ageGroup: AgeGroup.values()) 
			IOController.displayMessage(ageGroup.getLabel() + ": " + ageGroupCount.get(ageGroup));
		
		IOController.displayMessage("");
		IOController.displayMessage("Total Price: $" + String.format("%.2f", totalPrice));
		
		IOController.pressEnterToContinue();
	}
}
