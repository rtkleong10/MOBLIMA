package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * This class contains all the information of a pricing scheme
 */
public class PricingScheme implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 5342648434933852012L;

	/**
	 * The base price of the pricing scheme
	 */
	private double basePrice;
	/**
	 * The holiday dates of the pricing scheme
	 */
	private ArrayList<LocalDate> holidayDates = new ArrayList<LocalDate>();
	/**
	 * The cinema class multipliers of the pricing scheme
	 */
	private EnumMap<CinemaClass, Double> cinemaMultipliers = new EnumMap<CinemaClass, Double>(CinemaClass.class);
	/**
	 * The age group multipliers of the pricing scheme
	 */
	private EnumMap<AgeGroup, Double> ageMultipliers = new EnumMap<AgeGroup, Double>(AgeGroup.class);
	/**
	 * The movie type multipliers of the pricing scheme
	 */
	private EnumMap<MovieType, Double> movieMultipliers = new EnumMap<MovieType, Double>(MovieType.class);
	/**
	 * The date type multipliers of the pricing scheme
	 */
	private HashMap<DateType, Double> dateMultipliers = new HashMap<DateType, Double>();
	
	/**
     * This method returns the price of a ticket with the given date, cinema class, age group and movie type
     * @param date the date of the ticket
     * @param cinemaClass the cinema class of the ticket
     * @param ageGroup the age group of the ticket
     * @param movieType the movie type of the ticket
     * @return the price of the ticket
     */
	public double getPrice(LocalDate date, CinemaClass cinemaClass, AgeGroup ageGroup, MovieType movieType) {
		double price = this.basePrice;
		
		Double cinemaMultiplier = this.getCinemaMultiplier(cinemaClass);
		if (cinemaMultiplier != null)
			price *= cinemaMultiplier;
		
		Double ageMultiplier = this.getAgeMultiplier(ageGroup);
		if (ageMultiplier != null)
			price *= ageMultiplier;

		Double movieMultiplier = this.getMovieMultiplier(movieType);
		if (movieMultiplier != null)
			price *= movieMultiplier;
		
		Double dateMultiplier = this.getDateMultiplier(date);
		if (dateMultiplier != null)
			price *= dateMultiplier;

		return price;
	}
	
	/**
     * This method returns the list of holidays
     * @return the list of holidays
     */
	public ArrayList<LocalDate> getHolidayDates() {
		return this.holidayDates;
	}
	
	/**
     * This method returns true if the given date is a holiday
     * @param date the date to check whether it's a holiday
     * @return true if the given date is a holiday, false if not
     */
	private boolean isHoliday(LocalDate date) {
		for (LocalDate holidayDate: holidayDates) {
			if (date.equals(holidayDate))
				return true;
		}

		return false;
	}

	/**
     * This method returns the base price of the pricing scheme.
     * @return the base price of the pricing scheme
     */
	public double getBasePrice() {
		return this.basePrice;
	}
	
	/**
     * This method sets the base price of the pricing scheme.
     * @param basePrice the new base price of the pricing scheme
     */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	/**
     * This method returns the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to get the multiplier of
     * @return the multiplier corresponding to the cinema class
     */
	public Double getCinemaMultiplier(CinemaClass cinemaClass) {
		return this.cinemaMultipliers.get(cinemaClass);
	}
	
	/**
     * This method sets the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to set the multiplier of
     * @param cinemaMultiplier the new multiplier for the cinema class
     */
	public void setCinemaMultiplier(CinemaClass cinemaClass, Double cinemaMultiplier) {
		this.cinemaMultipliers.put(cinemaClass, cinemaMultiplier);
	}
	
	/**
     * This method returns the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to get the multiplier of
     * @return the multiplier corresponding to the age group
     */
	public Double getAgeMultiplier(AgeGroup ageGroup) {
		return this.ageMultipliers.get(ageGroup);
	}
	
	/**
     * This method sets the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to set the multiplier of
     * @param ageMultiplier the new multiplier for the age group
     */
	public void setAgeMultiplier(AgeGroup ageGroup, Double ageMultiplier) {
		this.ageMultipliers.put(ageGroup, ageMultiplier);
	}
	
	/**
     * This method returns the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to get the multiplier of
     * @return the multiplier corresponding to the movie type
     */
	public Double getMovieMultiplier(MovieType movieType) {
		return this.movieMultipliers.get(movieType);
	}
	
	/**
     * This method sets the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to set the multiplier of
     * @param movieMultiplier the new multiplier for the movie type
     */
	public void setMovieMultiplier(MovieType movieType, Double movieMultiplier) {
		this.movieMultipliers.put(movieType, movieMultiplier);
	}
	
	/**
     * This method returns the corresponding {@code DateType} of a date.
     * If the date is in the pricing scheme's holidays, regardless of the day of the week, then it will return {@code DateType.HOLIDAY}.
     * Otherwise, it return {@code DateType.WEEKEND} and {@code DateType.WEEKDAY} for weekends and weekdays respectively.
     * @param date
     * @return the corresponding {@code DateType} of a date.
     */
	private DateType getDateType(LocalDate date) {
		if (this.isHoliday(date))
			return DateType.HOLIDAY;
		
		else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
			return DateType.WEEKEND;
		
		else
			return DateType.WEEKDAY;
	}
	
	/**
     * This method returns the the multiplier corresponding to a given date.
     * @param date the date to get the multiplier of
     * @return the multiplier corresponding to the date
     */
	public Double getDateMultiplier(LocalDate date) {
		return this.dateMultipliers.get(getDateType(date));
	}
	
	/**
	 * This method returns the the multiplier corresponding to a given date type.
	 * @param dateType the date type to get the multiplier of
	 * @return the multiplier corresponding to the date type
	 */
	public Double getDateMultiplier(DateType dateType) {
		return this.dateMultipliers.get(dateType);
	}
	
	/**
     * This method sets the the multiplier corresponding to a given date type.
     * @param dateType the date type to set the multiplier of
     * @param dateMultiplier the new multiplier for the date type
     */
	public void setDateMultiplier(DateType dateType, Double dateMultiplier) {
		this.dateMultipliers.put(dateType, dateMultiplier);
	}
}
