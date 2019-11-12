package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.AgeGroup;
import model.Cinema;
import model.Cineplex;
import model.Movie;
import model.PricingScheme;
import model.ShowTime;
import view.BookingView1;
import view.ShowTimeView;

public class BookingManager {
 
	public static void main (String []args) {
		DataManager.initialise();
		DataManager.load();
		
		int choice;
		Scanner sc = new Scanner (System.in);
		
		BookingView1 bookView = new BookingView1();
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		
		bookView.printCineplex(cineplexList);
		choice = sc.nextInt();
		
		System.out.println("");
		
		List<ShowTime> showList = new ArrayList<>();
		List<Cineplex> selectedCineplex =  new ArrayList<>();
		selectedCineplex.add(cineplexList.get(choice-1));
		showList = cineplexList.get(choice-1).getShowTimes();
		
		Map<Movie, List <ShowTime>> byMovie = showList.stream()
				.collect(Collectors.groupingBy(ShowTime::getMovie));    
	
		List <String> movieNames =  new ArrayList<>();
		for (Map.Entry<Movie, List <ShowTime>> entry: byMovie.entrySet()) {
			 movieNames.add(entry.getKey().getTitle());
		}
		bookView.printMovieNames(movieNames);
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
		
		bookView.printShowTime(possibleShow);
		choice = sc.nextInt();
		ShowTime selectedShow = possibleShow.get(choice-1);
		
		Cinema selectedCinema = selectedShow.getCinema();
		bookView.displaySeat(selectedShow);
		boolean[][] selectedSeat = new boolean[selectedShow.getLayout().length][];
		boolean done = false;
		
		if(selectedShow.checkFull()) {
			System.out.println("Sorry Fully Booked");
			return ;
		}

		selectedSeat = bookView.getSeat(selectedShow);

		do {
			if(selectedShow.checkAvail(selectedSeat)) {
				done = true;
			}
			else {
				System.out.println("Unavailable seat selected");
				System.out.println("Select seat again");
				selectedSeat = bookView.getSeat(selectedShow);
			}
		}while(!done);
		
		int [] ageGrp = new int[3];
		System.out.println("How many of each age group [Child Adult Elderly]:");
		for(int i=0; i<ageGrp.length; i++) {
			ageGrp[i] = sc.nextInt();
		}
		
		double price = calPrice(selectedShow,selectedCinema, ageGrp);
		
		
		bookView.printBookInfo(selectedShow, ageGrp, price);
		System.out.print("Confirm Booking [y/n]: ");
		char confirm = sc.next().charAt(0);
		
		String transaction =getTid(selectedCinema);
		if(confirm == 'y') {
			//need to input moviegoer
			selectedShow.createBooking(transaction, null, selectedSeat, price );
			System.out.println("\nBooking Successful");
			System.out.println("Transaction ID: " + transaction);
			bookView.displaySeat(selectedShow);
		}
		else
			System.out.println("Booking Cancelled");
	}
	/**
	 * Calculates the price of booking transaction
	 * @param selectedShow showtime selected by user
	 * @param selectedCinema cinema of the showtime selected by user
	 * @param ageGrp number of booking for each age group
	 * @return price of booking transaction
	 */
	private static double calPrice(ShowTime selectedShow, Cinema selectedCinema, int ageGrp[]) {
		PricingScheme p = new PricingScheme();
		double price=0;
		price += ageGrp[0]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.CHILD, selectedShow.getMovie().getMovieType());
		price += ageGrp[1]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.ADULT, selectedShow.getMovie().getMovieType());
		price += ageGrp[2]*p.getPrice(selectedShow.getDate(), selectedCinema.getCinemaClass(), AgeGroup.SENIOR_CITIZEN, selectedShow.getMovie().getMovieType());
		return price;
	}
	/**
	 * Generates transaction id
	 * @param selectedCinema cinema of the showtime selected by user
	 * @return booking transaction id 
	 */
	private static String getTid(Cinema selectedCinema) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = selectedCinema.getCinemaCode() + LocalDateTime.now().format(formatter);
		return transaction;
	}
}
