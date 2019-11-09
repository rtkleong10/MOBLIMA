package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;
import model.*;
import view.ShowTimeView;

import java.io.PrintStream;


public class BookingApp {
	
	
	public static void main (String args[]) {
		DataManager.initialise();

		DataManager.load();
		
		int choice;
		Scanner sc = new Scanner (System.in);
		System.out.println("Select Cineplex: ");
		System.out.print("1) Golden Village\n"
				+ 			"2) Cathay Cineplex\n"
				+			"3) Shaw Theater\n");
		System.out.print("Option: ");
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		
		choice = sc.nextInt();
		ArrayList<Cinema> cinList = new ArrayList<>();
		ArrayList<Movie> movieList = new ArrayList<>();
		
		List<ShowTime> showList = new ArrayList<>();
		
		List<Cineplex> selectedCineplex =  new ArrayList<>();
		selectedCineplex.add(cineplexList.get(choice-1));
		System.out.println("");
		
		
		showList = ShowTimeView.getShowTimes(cineplexList.get(choice-1));

		Map<Movie, List <ShowTime>> byMovie = showList.stream()
				.collect(Collectors.groupingBy(ShowTime::getMovie));    
	
		List <String> movieNames =  new ArrayList<>();
		for (Map.Entry<Movie, List <ShowTime>> entry: byMovie.entrySet()) {
			 movieNames.add(entry.getKey().getTitle());
		}
		
		System.out.println("Select Movie:");
		for (int i=0; i<movieNames.size(); i++) {
			System.out.println( i+1 +") "+ movieNames.get(i));
		}
		System.out.print("Option: ");
		choice = sc.nextInt();
		
		List <ShowTime> possibleShow = new ArrayList <>();
		int count = 1;
		for (Map.Entry<Movie, List <ShowTime>> entry: byMovie.entrySet()) {
			if(count == choice) {
				possibleShow.addAll(entry.getValue());
				break;
			}
			count++;
		}
		
		System.out.println("\nSelect Showtime :");
		for (int i=0; i<possibleShow.size(); i++) {
			Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getStartTime);
			possibleShow.sort(dateComparator);
			System.out.println( i+1 +") "+ possibleShow.get(i).getStartTime().toLocalDate()+"  "+ 
								possibleShow.get(i).getStartTime().toLocalTime());
		}
		System.out.print("Option: ");
		choice = sc.nextInt();
		ShowTime selectedShow = possibleShow.get(choice-1);
		
		//findCinema 
		Cinema selectedCinema = selectedShow.getCinema();
		
		displaySeat(selectedShow);
		boolean[][] selectedSeat = new boolean[selectedShow.getLayout().length][];
		boolean done = false;
		
		if(selectedShow.checkFull()) {
			System.out.println("Sorry Fully Booked");
			return ;
		}

		selectedSeat = getSeat(selectedShow);

		
		do {
			if(selectedShow.checkAvail(selectedSeat)) {
				done = true;
			}
			else {
				System.out.println("Unavailable seat selected");
				System.out.println("Select seat again");
				selectedSeat = getSeat(selectedShow);
			}
		}while(!done);
		
		int [] ageGrp = new int[3];
		System.out.println("How many of each age group [Child Adult Elderly]:");
		for(int i=0; i<ageGrp.length; i++) {
			ageGrp[i] = sc.nextInt();
		}
		
		double price = 0;
		PricingScheme p = new PricingScheme();
		price += ageGrp[0]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.CHILD, selectedShow.getMovie().getMovieType());
		price += ageGrp[1]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.ADULT, selectedShow.getMovie().getMovieType());
		price += ageGrp[2]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.SENIOR_CITIZEN, selectedShow.getMovie().getMovieType());
		
		
		System.out.println("   === Booking Information ===");
		ShowTimeView.displayShowTime(Arrays.asList(selectedShow));
		System.out.println("Child: "+ ageGrp[0]);
		System.out.println("Adult: "+ ageGrp[1]);
		System.out.println("Child: "+ ageGrp[2]);
		System.out.printf("Price : $%.2f\n",price);
		System.out.print("Confirm Booking [y/n]: ");
		
		char confirm = sc.next().charAt(0);
		String transaction = selectedCinema.getCinemaCode();
		if(confirm == 'y') {
			//need to input moviegoer
			selectedShow.createBooking(transaction, null, selectedSeat, price );
			System.out.println("Success Booking");
			
			displaySeat(selectedShow);
		}
		else
			System.out.println("Booking Cancelled");
			
		
		
		
	}
	
	public static boolean[][] getSeat (ShowTime show){
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
	
	public static  void displaySeat (ShowTime show) {
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


}