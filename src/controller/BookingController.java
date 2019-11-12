package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;

import model.AgeGroup;
import model.Booking;
import model.Cineplex;
import model.DataManager;
import model.Movie;
import model.MovieGoer;
import model.PricingScheme;
import model.ShowTime;
import view.BookingView;
import view.IOController;
import view.MenuView;

public class BookingController implements Controller {
	private MovieGoer movieGoer;
	private Cineplex cineplex;
	private Movie movie;
	private ShowTime showTime;

	public BookingController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		selectCineplex();
		
		if (this.cineplex == null) {
			NavigationController.goBack();
			return;
		}
		
		selectMovie();
		
		if (this.movie == null) {
			NavigationController.goBack();
			return;
		}
		
		selectShowTime();
		
		if (this.showTime == null) {
			NavigationController.goBack();
			return;
		}
		
		if (showTime.checkFull()) {
			IOController.displayMessage("Sorry, this show time is fully booked");
			NavigationController.goBack();
			return;
		}
		
		BookingView.displaySeats(showTime);
		
		int number = IOController.readInt("How many seats would you like to book: ");
		boolean[][] selectedSeats = BookingView.getSeats(number, showTime);
		EnumMap<AgeGroup, Integer> ageGroupCount = BookingView.getAgeGroupCount(number);
		double totalPrice = calculatePrice(ageGroupCount);
		
		boolean confirm = IOController.readBoolean("Confirm Booking [y/n]: ", "y", "n");
		
		if (confirm) {
			Booking booking = showTime.createBooking(movieGoer, selectedSeats, totalPrice);
			IOController.displayMessage("\nBooking Successful");
			IOController.displayMessage("Transaction ID: " + booking.getTransactionId());
			
		} else {
			IOController.displayMessage("Booking Cancelled");
		}
		
		BookingView.displaySeats(showTime);
		
		BookingView.printBookInfo(showTime, ageGroupCount, totalPrice);
		
		NavigationController.goBack();
	}
	
	private void selectCineplex() {
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		int size = cineplexList.size();
		String[] cineplexStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			cineplexStrings[i] = cineplexList.get(i).getName();
		}
		
		cineplexStrings[size] = "Exit";
		
		int option = MenuView.getMenuOption(
			"Select a cineplex",
			cineplexStrings
		);
		
		option--;
		
		if (option != size)
			this.cineplex = cineplexList.get(option);
	}
	
	private void selectMovie() {
		ArrayList<Movie> movieList = getMovieList();
		int size = movieList.size();
		String[] movieStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			movieStrings[i] = movieList.get(i).getTitle();
		}
		
		movieStrings[size] = "Exit";
		
		int option = MenuView.getMenuOption(
			"Select a movie",
			movieStrings
		);
		
		option--;
		
		if (option != size)
			this.movie = movieList.get(option);
	}
	
	private void selectShowTime() {
		ArrayList<ShowTime> showTimeList = getShowTimeList();
		int size = showTimeList.size();
		String[] showTimeStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			showTimeStrings[i] = showTimeList.get(i).getDate() + "  " + showTimeList.get(i).getStartTime().toLocalTime();
		}
		
		showTimeStrings[size] = "Exit";
		
		int option = MenuView.getMenuOption(
			"Select a movie",
			showTimeStrings
		);
		
		option--;
		
		if (option != size)
			this.showTime = showTimeList.get(option);
	}
	
	public ArrayList<Movie> getMovieList() {
		ArrayList<ShowTime> showTimeList = cineplex.getShowTimes();    
		ArrayList<Movie> movieList = new ArrayList<Movie>();
				
		for (ShowTime showTime: showTimeList) {
			Movie movie = showTime.getMovie();
			if (!movieList.contains(movie))
				movieList.add(movie);
		}
		
		return movieList;
	}
	
	public ArrayList<ShowTime> getShowTimeList() {
		ArrayList<ShowTime> showTimeList = cineplex.getShowTimes();    
		ArrayList<ShowTime> movieShowTimeList = new ArrayList<ShowTime>();
				
		for (ShowTime showTime: showTimeList)
			if (showTime.getMovie() == movie)
				movieShowTimeList.add(showTime);
		
		Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getStartTime);
		movieShowTimeList.sort(dateComparator);
		
		return movieShowTimeList;
	}
	
	/**
	 * Calculates the price of booking transaction
	 * @param selectedShow showtime selected by user
	 * @param selectedCinema cinema of the showtime selected by user
	 * @param ageGrp number of booking for each age group
	 * @return price of booking transaction
	 */
	public double calculatePrice(EnumMap<AgeGroup, Integer> ageGroupCount) {
		PricingScheme pricingScheme = DataManager.getDataStore().getPricingScheme();
		
		double totalPrice = 0;
		
		for (AgeGroup ageGroup : AgeGroup.values()) {
			totalPrice += ageGroupCount.get(ageGroup) * pricingScheme.getPrice(
				showTime.getDate(),
				showTime.getCinema().getCinemaClass(),
				ageGroup,
				showTime.getMovie().getMovieType()
			);
		}
		
		return totalPrice;
	}
}
