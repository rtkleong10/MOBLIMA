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
		this.cineplex = selectCineplex();
		this.movie = selectMovie();		
		this.showTime = selectShowTime();
		
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
	
	private Cineplex selectCineplex() {
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		Cineplex[] cineplexArr = new Cineplex[cineplexList.size()];
		cineplexList.toArray(cineplexArr);
		return MenuView.getLabelledItem("Select a Cineplex", cineplexArr);
	}
	
	private Movie selectMovie() {
		ArrayList<Movie> movieList = getMovieList();
		Movie[] movieArr = new Movie[movieList.size()];
		movieList.toArray(movieArr);
		return MenuView.getLabelledItem("Select a Movie", movieArr);
	}
	
	private ShowTime selectShowTime() {
		ArrayList<ShowTime> showTimeList = getShowTimeList();
		ShowTime[] showTimeArr = new ShowTime[showTimeList.size()];
		showTimeList.toArray(showTimeArr);
		return MenuView.getLabelledItem("Select a Show Time", showTimeArr);
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
