package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;

import model.AgeGroup;
import model.Cinema;
import model.Cineplex;
import model.Movie;
import model.PricingScheme;
import model.ShowTime;

public class BookingController {
	public static ArrayList<Movie> getMovieList(Cineplex cineplex) {
		ArrayList<ShowTime> showTimeList = cineplex.getShowTimes();    
		ArrayList<Movie> movieList = new ArrayList<Movie>();
				
		for (ShowTime showTime: showTimeList) {
			Movie movie = showTime.getMovie();
			if (!movieList.contains(movie))
				movieList.add(movie);
		}
		
		return movieList;
	}
	
	public static ArrayList<ShowTime> getShowTimeList(Cineplex cineplex, Movie movie) {
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
	public static double calculatePrice(ShowTime showTime, EnumMap<AgeGroup, Integer> ageGroupCount) {
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
	
	/**
	 * Generates transaction id
	 * @param selectedCinema cinema of the showtime selected by user
	 * @return booking transaction id 
	 */
	public static String getTid(Cinema selectedCinema) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String transaction = selectedCinema.getCinemaCode() + LocalDateTime.now().format(formatter);
		return transaction;
	}
}
