package Controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Model.*;

public class DataGenerator {
	
	public static void main(String[] args) {
		AppManager.initialize();
		
		CinemaStaff cinemaStaffList[] = {
			new CinemaStaff("cathy", "cathyishappy"),
			new CinemaStaff("bob", "bobishappy")
		};
		
		MovieGoer movieGoerList[] = {
			new MovieGoer("sally", "Sally Lee", 93245678, "sally@gmail.com",  AgeGroup.ADULT,"sallylikesmovies"),
			new MovieGoer("joe", "Joe Doe", 91234567, "joe@gmail.com", AgeGroup.CHILD ,"joelikesmovies"),
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
		
		//System.out.println(AccountManager.getCinemaStaff("cathy", "cathyishappy").getUsername());
		//System.out.println(AccountManager.getMovieGoer("sally", "sallylikesmovies").getName());
		System.out.println(pricingScheme.getPrice(LocalDate.of(2019, 1, 1), CinemaClass.NORMAL, AgeGroup.ADULT, MovieType.REGULAR));
		System.out.println(pricingScheme.getPrice(LocalDate.of(2019, 11, 5), CinemaClass.PLATINUM_MOVIE_SUITE, AgeGroup.CHILD, MovieType._3D));

		String[][] cast = {
			{"Daniel Radcliffe", "Emma Watson", "Rupert Grint"},
			{"Robert Downey Jr", "Mark Ruffalo", "Scarlett Johansson"},
			{"Harrison Ford", "Daisy Ridley", "Adam Driver"},
			{"Jon Heder", "Tina Majorino", "Jon Gries"},
			{"Will Arnett", "Lou Romano", "Patton Oswalt"},
			{"Al Pacino", "James Caan", "Diane Keaton"},
			{"Shakira", "Idris Elba",  "Jenny Slate"},
			{"Ward Horton", "Annabelle Walis", "Gabriel Bateman"}
		};
		
		Movie movieList[] = {
				new Movie("Harry Potter and the Goblet of Fire", "This movie sees Harry, Ron and Hermione returning for their 4th year in Hogwarts. There is an upcoming tournament between 3 schools where one partipant must from each school must compete in a dangerous quest.", "Mike Newell", cast[0], ReleaseRating.PG, MovieType.BLOCKBUSTER, Duration.ofMinutes(120)),
				new Movie("Avengers: Endgame", "The final movie of the Avengers Franchise where the remaining Avengers must figure out a way to defeat Thanos, and save the world and their other superhero friends", "Anthony Russo", cast[1], ReleaseRating.PG13, MovieType.BLOCKBUSTER, Duration.ofMinutes(90)),
				new Movie("Starwars: The Force Awakens", "A new order threatens to destroy the New Republic. Finn,  Rey and Poe,  backed by the Resistance and the Republic,  must find a way to stop them and find Luke,  the last surviving Jedi.", "J.J. Abrahams", cast[2], ReleaseRating.PG13, MovieType._3D, Duration.ofMinutes(105)),
				new Movie("Napolean Dynamite", "Napoleon,  a socially awkward teenager,  gets caught up in his dysfunctional family's misadventures while trying to help a friend win the class presidency.", "Jared Hess", cast[3], ReleaseRating.PG13, MovieType.REGULAR, Duration.ofMinutes(135)),
				new Movie("Ratatouille", "Remy,  a rat,  aspires to become a renowned French chef. He doesn't realise that people despise rodents and will never enjoy a meal cooked by him.", "Brad Bird", cast[4], ReleaseRating.G, MovieType.REGULAR, Duration.ofMinutes(150)),
				new Movie("The Godfather", "Don Vito Corleone, head of a mafia family, decides to handover his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.", "Francis Coppola", cast[5], ReleaseRating.NC16, MovieType.REGULAR, Duration.ofMinutes(135)),
				new Movie("Zootopia", "Judy Hopps and Nick Wilde team up to uncover the mystery behind the fourteen missing predators. They end up finding out that the conspiracy is larger than it seems.", "Rich Moore", cast[6], ReleaseRating.G, MovieType.REGULAR, Duration.ofMinutes(165)),
				new Movie("Annabelle", "John and Mia are attacked by a couple, who are worshippers of Satan. However, before the cops kill them, the couple use a doll as a conduit to make John and Mia's life miserable.", "John Leonetti", cast[7], ReleaseRating.NC16, MovieType.BLOCKBUSTER, Duration.ofMinutes(125))
		};
		
		ArrayList<Movie> fullMovieList = CineplexManager.getMovieList();
		
		for (Movie movie: movieList) {
			fullMovieList.add(movie);
		}
		
		AppManager.update();
	}
}

