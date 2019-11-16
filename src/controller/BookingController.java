package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import model.AgeGroup;
import model.Booking;
import model.Cineplex;
import model.DataManager;
import model.Movie;
import model.MovieGoer;
import model.PricingScheme;
import model.ShowTime;
import model.ShowingStatus;
import view.BookingView;
import view.IOController;
import view.MenuView;

/**
 * This class handles the control flow of movie booking for a movie goer
 */
public class BookingController implements Controller {
	/**
	 * The movie goer who wants to do the booking
	 */
	private MovieGoer movieGoer;
	/**
	 * The show time that the movie goer wants to book
	 */
	private ShowTime showTime;

	/**
	 * Creates a new {@code BookingController} object for the given movie goer
	 * @param movieGoer the movie goer who wants to do the booking
	 */
	public BookingController(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {		
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
		
		boolean confirm = IOController.readBoolean("Confirm booking (y/n): ", "y", "n");
		
		if (confirm) {
			Booking booking = showTime.createBooking(movieGoer, selectedSeats, totalPrice);
			BookingView.displaySeats(showTime);
			IOController.displayMessage("Booking successful!");
			IOController.displayMessage("Transaction ID: " + booking.getTransactionId());
			BookingView.printBookInfo(ageGroupCount, totalPrice);
			
		} else {
			IOController.displayMessage("Booking cancelled");
		}
		
		NavigationController.goBack();
	}
	
	/**
	 * This method is used to control the flow of the show time selction. The movie goer is asked to select a cineplex, then a movie, then a show time.
	 * @return the selected  {@code ShowTime} object
	 */
	private ShowTime selectShowTime() {
		// Select a cineplex
		List<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		Cineplex cineplex = MenuView.getLabelledItem("Select a Cineplex", cineplexList);
	
		// Group show times by movie
		List<ShowTime> showTimeList = cineplex.getShowTimes();
		Map<Movie, List<ShowTime>> showTimesByMovie = showTimeList.stream().collect(Collectors.groupingBy(ShowTime::getMovie));
		
		// Select a movie
		List<Movie> movieList = new ArrayList<Movie>();
		
		for (Movie movie: showTimesByMovie.keySet()) {
			ShowingStatus showingStatus = movie.getShowingStatus();
			if (showingStatus == ShowingStatus.PREVIEW || showingStatus == ShowingStatus.NOW_SHOWING)
				movieList.add(movie);
		}
		
		Movie movie = MenuView.getLabelledItem("Select a movie", movieList);
		
		// Select a show time
		List<ShowTime> movieShowTimeList = showTimesByMovie.get(movie);
		Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getStartDateTime);
		movieShowTimeList.sort(dateComparator);
		ShowTime showTime = MenuView.getLabelledItem("Select a Show Time", movieShowTimeList);
		
		return showTime;
	}
	
	/**
	 * This method calculates the price of booking transaction
	 * @param ageGroupCount an {@code EnumMap} containing the number of tickets for each age group
	 * @return the price of booking transaction
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
