package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.AgeGroup;
import model.CinemaClass;
import model.DateType;
import model.MovieType;
import model.PricingScheme;

public class PricingSchemeEditView {
	
	public static void updateBasePrice(PricingScheme pricingScheme) {
		IOController.displayMessage("Current base price: " + pricingScheme.getBasePrice());
		double basePrice = IOController.readDouble("Base price: ");
		pricingScheme.setBasePrice(basePrice);
	}
	
	public static void updateMultipliers(PricingScheme pricingScheme) {
		int option = MenuView.getMenuOption(
			"Select a type of multiplier",
			"Cinema Class",
			"Age Group",
			"Movie Type",
			"Date Type"
		);
		
		switch (option) {
			case 1:
				updateCinemaClassMultiplier(pricingScheme);
				break;
				
			case 2:
				updateAgeGroupMultiplier(pricingScheme);
				break;
				
			case 3:
				updateMovieTypeMultiplier(pricingScheme);
				break;
				
			case 4:
				updateDateTypeMultiplier(pricingScheme);
				break;
		}
	}
	
	public static void updateHolidays(PricingScheme pricingScheme) {
		int option = MenuView.getMenuOption(
			"What would you like to do?",
			"Add holiday",
			"Remove holiday"
		);
		
		switch (option) {
			case 1:
				addHoliday(pricingScheme);
				break;
				
			case 2:
				removeHoliday(pricingScheme);
				break;
		}
	}
	
	private static void updateCinemaClassMultiplier(PricingScheme pricingScheme) {
		CinemaClass cinemaClass = MenuView.getLabelledItem("Select a cinema class", CinemaClass.values());
		IOController.displayMessage("Current multiplier: " + pricingScheme.getCinemaMultiplier(cinemaClass));
		double cinemaMultiplier = IOController.readDouble("Multiplier: ");
		pricingScheme.setCinemaMultiplier(cinemaClass, cinemaMultiplier);
	}
	
	private static void updateAgeGroupMultiplier(PricingScheme pricingScheme) {
		AgeGroup ageGroup = MenuView.getLabelledItem("Select an age group", AgeGroup.values());
		IOController.displayMessage("Current multiplier: " + pricingScheme.getAgeMultiplier(ageGroup));
		double ageMultiplier = IOController.readDouble("Multiplier: ");
		pricingScheme.setAgeMultiplier(ageGroup, ageMultiplier);
	}
	
	private static void updateMovieTypeMultiplier(PricingScheme pricingScheme) {
		MovieType movieType = MenuView.getLabelledItem("Select a movie type", MovieType.values());
		IOController.displayMessage("Current multiplier: " + pricingScheme.getMovieMultiplier(movieType));
		double movieMultiplier = IOController.readDouble("Multiplier: ");
		pricingScheme.setMovieMultiplier(movieType, movieMultiplier);
	}
	
	private static void updateDateTypeMultiplier(PricingScheme pricingScheme) {
		DateType dateType = MenuView.getLabelledItem("Select a cinema class", DateType.values());
		IOController.displayMessage("Current multiplier: " + pricingScheme.getDateMultiplier(dateType));
		double dateMultiplier = IOController.readDouble("Multiplier: ");
		pricingScheme.setDateMultiplier(dateType, dateMultiplier);
	}
	
	private static void addHoliday(PricingScheme pricingScheme) {
		LocalDate holidayDate = IOController.readDate("Holiday date (dd/mm/yyyy): ");
		pricingScheme.getHolidayDates().add(holidayDate);
	}
	
	private static void removeHoliday(PricingScheme pricingScheme) {
		List<LocalDate> holidayDates = pricingScheme.getHolidayDates();
		int size = holidayDates.size();
		String[] holidayDateStrings = new String[size];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (int i = 0; i < size; i++)
			holidayDateStrings[i] = holidayDates.get(i).format(formatter);
		
		int option = MenuView.getMenuOption("Select a holiday date", holidayDateStrings);
		LocalDate selectedHolidayDate = holidayDates.get(option - 1);
		pricingScheme.getHolidayDates().remove(selectedHolidayDate);
	}
}
