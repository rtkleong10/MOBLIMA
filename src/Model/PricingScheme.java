package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;

public class PricingScheme implements Serializable {
	private static final long serialVersionUID = 5342648434933852012L;

	private double basePrice;
	private HashMap<LocalDate, String> holidays = new HashMap<LocalDate, String>();
	private EnumMap<CinemaClass, Double> cinemaMultipliers = new EnumMap<CinemaClass, Double>(CinemaClass.class);
	private EnumMap<AgeGroup, Double> ageMultipliers = new EnumMap<AgeGroup, Double>(AgeGroup.class);
	private EnumMap<MovieType, Double> movieMultipliers = new EnumMap<MovieType, Double>(MovieType.class);
	private HashMap<DateType, Double> dateMultipliers = new HashMap<DateType, Double>();
	
	/**
     * Returns the price of a ticker with its date, cinema class, age group and movie type specified.
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
     * Returns the hash map of the holidays.
     * @return the hash map of the holidays
     */
	public HashMap<LocalDate, String> getHolidays() {
		return this.holidays;
	}
	
	/**
     * Removes the holiday corresponding to the holiday date.
     * @param holidayDate the date of the holiday to remove
     */
	public void removeHolidayDate(LocalDate holidayDate) {
		this.holidays.remove(holidayDate);
	}
	
	/**
     * Adds the holiday with its date and name specified.
     * @param holidayDate the date of the holiday to add
     * @param holidayName the name of the holiday to add
     */
	public void addHolidayDates(LocalDate holidayDate, String holidayName) {
		this.holidays.put(holidayDate, holidayName);
	}
	
	/**
     * Returns true if the given date is a holiday.
     * @param holiday the password to test against the password the movie goer
     * @return true if the password was correct, false if not
     */
	private boolean isHoliday(LocalDate date) {
		for (LocalDate holidayDate: holidays.keySet()) {
			if (date.equals(holidayDate))
				return true;
		}

		return false;
	}

	/**
     * Returns the base price of the pricing scheme.
     * @return the base price of the pricing scheme
     */
	public double getBasePrice() {
		return this.basePrice;
	}
	
	/**
     * Sets the base price of the pricing scheme.
     * @param basePrice the new base price of the pricing scheme
     */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	/**
     * Returns the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to get the multiplier of
     * @return the multiplier corresponding to the cinema class
     */
	public Double getCinemaMultiplier(CinemaClass cinemaClass) {
		return this.cinemaMultipliers.get(cinemaClass);
	}
	
	/**
     * Sets the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to set the multiplier of
     * @param cinemaMultiplier the new multiplier for the cinema class
     */
	public void setCinemaMultiplier(CinemaClass cinemaClass, Double cinemaMultiplier) {
		this.cinemaMultipliers.put(cinemaClass, cinemaMultiplier);
	}
	
	/**
     * Returns the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to get the multiplier of
     * @return the multiplier corresponding to the age group
     */
	public Double getAgeMultiplier(AgeGroup ageGroup) {
		return this.ageMultipliers.get(ageGroup);
	}
	
	/**
     * Sets the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to set the multiplier of
     * @param ageMultiplier the new multiplier for the age group
     */
	public void setAgeMultiplier(AgeGroup ageGroup, Double ageMultiplier) {
		this.ageMultipliers.put(ageGroup, ageMultiplier);
	}
	
	/**
     * Returns the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to get the multiplier of
     * @return the multiplier corresponding to the movie type
     */
	public Double getMovieMultiplier(MovieType movieType) {
		return this.movieMultipliers.get(movieType);
	}
	
	/**
     * Sets the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to set the multiplier of
     * @param movieMultiplier the new multiplier for the movie type
     */
	public void setMovieMultiplier(MovieType movieType, Double movieMultiplier) {
		this.movieMultipliers.put(movieType, movieMultiplier);
	}
	
	/**
     * Returns the corresponding {@code DateType} of a date.
     * If the date is in the pricing scheme's holidays, regardless of the day of the week, then it will return {@code DateType.HOLIDAY}.
     * Otherwise, it return {@code DateType.WEEKEND} and {@code DateType.WEEKDAY} for weekends and weekdays respectively.
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
     * Returns the the multiplier corresponding to a given date.
     * @param date the date to get the multiplier of
     * @return the multiplier corresponding to the date
     */
	public Double getDateMultiplier(LocalDate date) {
		return this.dateMultipliers.get(getDateType(date));
	}
	
	/**
     * Sets the the multiplier corresponding to a given date type.
     * @param dateType the date type to set the multiplier of
     * @param dateMultiplier the new multiplier for the date type
     */
	public void setDateMultiplier(DateType dateType, Double dateMultiplier) {
		this.dateMultipliers.put(dateType, dateMultiplier);
	}
}
