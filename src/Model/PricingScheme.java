package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;

public class PricingScheme implements Serializable {
	private static final long serialVersionUID = 5342648434933852012L;

	private double basePrice;
	private HashMap<LocalDate, String> holidays = new HashMap<>();
	private EnumMap<CinemaClass, Double> cinemaMultipliers = new EnumMap<>(CinemaClass.class);
	private EnumMap<AgeGroup, Double> ageMultipliers = new EnumMap<>(AgeGroup.class);
	private EnumMap<MovieType, Double> movieMultipliers = new EnumMap<>(MovieType.class);
	private HashMap<DateType, Double> dateMultipliers = new HashMap<>();
	
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
	
	public HashMap<LocalDate, String> getHolidays() {
		return this.holidays;
	}

	public void removeHolidayDate(LocalDate holidayDate) {
		this.holidays.remove(holidayDate);
	}
	
	public void addHolidayDates(LocalDate holidayDate, String holidayName) {
		this.holidays.put(holidayDate, holidayName);
	}
	
	private boolean isHoliday(LocalDate date) {
		for (LocalDate holidayDate: getHolidays().keySet()) {
			if (date.equals(holidayDate))
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

	public Double getCinemaMultiplier(CinemaClass cinemaClass) {
		return this.cinemaMultipliers.get(cinemaClass);
	}

	public void setCinemaMultiplier(CinemaClass cinemaClass, Double cinemaMultiplier) {
		this.cinemaMultipliers.put(cinemaClass, cinemaMultiplier);
	}

	public Double getAgeMultiplier(AgeGroup ageGroup) {
		return this.ageMultipliers.get(ageGroup);
	}

	public void setAgeMultiplier(AgeGroup ageGroup, Double ageMultiplier) {
		this.ageMultipliers.put(ageGroup, ageMultiplier);
	}

	public Double getMovieMultiplier(MovieType movieType) {
		return this.movieMultipliers.get(movieType);
	}

	public void setMovieMultiplier(MovieType movieType, Double movieMultiplier) {
		this.movieMultipliers.put(movieType, movieMultiplier);
	}
	
	public Double getDateMultiplier(LocalDate date) {
		if (this.isHoliday(date))
			return this.dateMultipliers.get(DateType.HOLIDAY);
		
		else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
			return this.dateMultipliers.get(DateType.WEEKEND);
		
		else
			return this.dateMultipliers.get(DateType.WEEKDAY);
	}

	public void setDateMultiplier(DateType dateType, Double dateMultiplier) {
		this.dateMultipliers.put(dateType, dateMultiplier);
	}
}
