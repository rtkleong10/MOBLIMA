package Controller;

import java.util.HashMap;
import java.time.LocalDate;

import Model.*;

public class Main {
	public static void main(String[] args) {
		AppManager.initialize();
		
		CinemaStaff cinemaStaffList[] = {
			new CinemaStaff("cathy", "cathyishappy"),
			new CinemaStaff("bob", "bobishappy")
		};
		
		MovieGoer movieGoerList[] = {
			new MovieGoer("sally", "Sally Lee", 93245678, "sally@gmail.com", "sallylikesmovies"),
			new MovieGoer("joe", "Joe Doe", 91234567, "joe@gmail.com", "joelikesmovies"),
		};
		
		for (CinemaStaff cinemaStaff: cinemaStaffList) {
			AccountManager.addCinemaStaff(cinemaStaff);
		}
		
		for (MovieGoer movieGoer: movieGoerList) {
			AccountManager.addMovieGoer(movieGoer);
		}
		
		PricingScheme pricingScheme = CineplexManager.getPricingScheme();
		
		pricingScheme.setBasePrice(10.0);
		
		pricingScheme.setDateMultiplier(DateType.WEEKDAY, 1.0);
		pricingScheme.setDateMultiplier(DateType.WEEKEND, 1.1);
		pricingScheme.setDateMultiplier(DateType.HOLIDAY, 1.2);
		
		pricingScheme.setCinemaMultiplier(CinemaClass.NORMAL, 1.0);
		pricingScheme.setCinemaMultiplier(CinemaClass.PLATINUM_MOVIE_SUITE, 2.0);
		
		pricingScheme.setAgeMultiplier(AgeGroup.CHILD, 0.5);
		pricingScheme.setAgeMultiplier(AgeGroup.ADULT, 1.0);
		pricingScheme.setAgeMultiplier(AgeGroup.SENIOR_CITIZEN, 0.8);
		
		pricingScheme.setMovieMultiplier(MovieType.REGULAR, 1.0);
		pricingScheme.setMovieMultiplier(MovieType.BLOCKBUSTER, 1.2);
		pricingScheme.setMovieMultiplier(MovieType._3D, 1.5);
		
		HashMap<LocalDate, String> holidays = new HashMap<LocalDate, String>();

		holidays.put(LocalDate.of(2019, 1, 1), "New Year's Day");
		holidays.put(LocalDate.of(2019, 2, 5), "Chinese New Year");
		holidays.put(LocalDate.of(2019, 2, 6), "Chinese New Year");
		holidays.put(LocalDate.of(2019, 4, 19), "Good Friday");
		holidays.put(LocalDate.of(2019, 5, 1), "Labour Day");
		holidays.put(LocalDate.of(2019, 5, 19), "Vesak");
		holidays.put(LocalDate.of(2019, 6, 5), "Hari Raya Puasa");
		holidays.put(LocalDate.of(2019, 8, 9), "National Day of Singapore");
		holidays.put(LocalDate.of(2019, 8, 11), "Hari Raya Haji");
		holidays.put(LocalDate.of(2019, 10, 27), "Diwali");
		holidays.put(LocalDate.of(2019, 12, 25), "Christmas Day");

		for (LocalDate holidayDate: holidays.keySet()) {
			pricingScheme.addHolidayDates(holidayDate, holidays.get(holidayDate));
		}
		
		System.out.println(AccountManager.getCinemaStaff("cathy", "cathyishappy").getUsername());
		System.out.println(AccountManager.getMovieGoer("sally", "sallylikesmovies").getName());
		System.out.println(pricingScheme.getPrice(LocalDate.of(2019, 1, 1), CinemaClass.NORMAL, AgeGroup.ADULT, MovieType.REGULAR));
		System.out.println(pricingScheme.getPrice(LocalDate.of(2019, 11, 5), CinemaClass.PLATINUM_MOVIE_SUITE, AgeGroup.CHILD, MovieType._3D));
		
		AppManager.update();
	}
}
