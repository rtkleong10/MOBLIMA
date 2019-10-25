package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;

public class PricingScheme implements Serializable {
	private static final long serialVersionUID = 5342648434933852012L;
	
	private double basePrice;
	private EnumMap<CinemaClass, Double> cinemaMultipliers;
	private EnumMap<AgeGroup, Double> ageMultipliers;
	private EnumMap<MovieType, Double> movieMultipliers;
	private HashMap<Date, String> holidays;
	private double holidayMultiplier;
	
	public double getPrice(Date date, CinemaClass cinemaClass, AgeGroup ageGroup, MovieType movieType) {
		double price = this.basePrice;
		
		if (isHoliday(date))
			price *= getHolidayMultiplier();
		
		Double cinemaMultiplier = this.cinemaMultipliers.get(cinemaClass);
		if (cinemaMultiplier != null)
			price *= cinemaMultiplier;
		
		Double ageMultiplier = this.ageMultipliers.get(ageGroup);
		if (ageMultiplier != null)
			price *= ageMultiplier;
		
		Double movieMultiplier = this.movieMultipliers.get(movieType);
		if (movieMultiplier != null)
			price *= movieMultiplier;
		
		return price;
	}
	
	private boolean isHoliday(Date date) {
		for (Date holidayDate: getHolidays().keySet()) {
		    if (date == holidayDate)
		    	return true;
		}
		
		return false;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getCinemaMultiplier(CinemaClass cinemaClass) {
		return this.cinemaMultipliers.get(cinemaClass);
	}

	public void setCinemaMultiplier(CinemaClass cinemaClass, double cinemaMultiplier) {
		this.cinemaMultipliers.put(cinemaClass, cinemaMultiplier);
	}
	
	public double getAgeMultiplier(AgeGroup ageGroup) {
		return this.ageMultipliers.get(ageGroup);
	}

	public void setAgeMultiplier(AgeGroup ageGroup, double ageMultiplier) {
		this.ageMultipliers.put(ageGroup, ageMultiplier);
	}
	
	public double getMovieMultiplier(MovieType movieType) {
		return this.movieMultipliers.get(movieType);
	}

	public void setMovieMultiplier(MovieType movieType, double movieMultiplier) {
		this.movieMultipliers.put(movieType, movieMultiplier);
	}
	
	public HashMap<Date, String> getHolidays() {
		return this.holidays;
	}
	
	public void removeHolidayDate(Date holidayDate) {
		this.holidays.remove(holidayDate);
	}

	public void addHolidayDates(Date holidayDate, String holidayName) {
		this.holidays.put(holidayDate, holidayName);
	}

	public double getHolidayMultiplier() {
		return this.holidayMultiplier;
	}

	public void setHolidayMultiplier(double holidayMultiplier) {
		this.holidayMultiplier = holidayMultiplier;
	}
}
