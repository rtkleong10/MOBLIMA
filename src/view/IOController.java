package view;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This method contains utility functions for displaying and reading information to and from the console
 */
@SuppressWarnings("resource")
public class IOController {
	/**
	 * This method displays the given message with no endline
	 * @param message the message to be displayed
	 */
	public static void displayMessageInline(String message) {
		System.out.print(message);
	}
	
	/**
	 * This method displays the given message with an endline
	 * @param message the message to be displayed
	 */
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * This method displays the given title
	 * @param title the title to be displayed
	 */
	public static void displayTitle(String title) {
		displayMessage(title);
		
		String line = "";
		
		for (int i = 0; i < title.length(); i++)
			line += "-";
		
		displayMessage(line);
	}
	
	/**
	 * This method displays the given message centred in the given width
	 * @param message the message to be displayed
	 * @param width the width for the message to be centred in
	 */
	public static void displayMessageCentred(String message, int width) {
		String margin = "";
		for (int i = 0; i < (width - message.length()) / 2; i++)
			margin += " ";
		
		displayMessage(margin + message);
	}
	
	/**
	 * This method reads in a line as a string
	 * @param message the prompt for the input
	 * @return the read line
	 */
	public static String readLine(String message) {
		displayMessageInline(message);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	/**
	 * This method reads in a password as a string. The password is hidden if possible.
	 * @param message the prompt for the input
	 * @return the read password
	 */
	public static String readPassword(String message) {
		Console console = System.console();
		
        if (console != null) {
        	char[] passwordArray = console.readPassword(message);
            return new String(passwordArray);
            
        } else {
        	// Fallback
        	return readLine(message);
        }
	}
	
	/**
	 * This method reads in an integer
	 * @param message the prompt for the input
	 * @return the read integer
	 */
	public static int readInt(String message) {
		while (true) {
			try {
				displayMessageInline(message);
				Scanner sc = new Scanner(System.in);
				return sc.nextInt();
				
			} catch (InputMismatchException e) {
				displayMessage("Please enter an integer");
			}
		}
	}
	
	/**
	 * This method reads in a double
	 * @param message the prompt for the input
	 * @return the read double
	 */
	public static double readDouble(String message) {
		while (true) {
			try {
				displayMessageInline(message);
				Scanner sc = new Scanner(System.in);
				return sc.nextDouble();
				
			} catch (InputMismatchException e) {
				displayMessage("Please enter a number");
			}
		}
	}
	
	/**
	 * This method reads in a boolean by checking if the user returned the {@code yesString} or the {@code noString}
	 * @param message the prompt for the input
	 * @param yesString the string for {@code true}
	 * @param noString the string for {@code false}
	 * @return the read boolean
	 */
	public static boolean readBoolean(String message, String yesString, String noString) {
		while (true) {
			displayMessageInline(message);
			String input = readLine("");
			
			if (input.equals(yesString))
				return true;
			else if (input.equals(noString))
				return false;
			
			displayMessage("Error: Invalid input (input should be " + yesString + " or " + noString + ")");
		}
	}
	
	/**
	 * This method reads in a date time in the format: dd/mm/yyyy hh:mm
	 * @param message the prompt for the input
	 * @return the read date time
	 */
	public static LocalDateTime readDateTime(String message) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    
	    while (true) {
		    try {
		    	String dateTimeString = readLine(message);
		    	return LocalDateTime.parse(dateTimeString, formatter);
		    	
		    } catch (DateTimeParseException e) {
		    	displayMessage("Please enter a valid date time of format: dd/mm/yyyy hh:mm");
		    }
	    }
	}
	
	/**
	 * This method reads in a date in the format: dd/mm/yyyy
	 * @param message the prompt for the input
	 * @return the read date
	 */
	public static LocalDate readDate(String message) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		while (true) {
		    try {
		    	String dateString = readLine(message);
		    	return LocalDate.parse(dateString, formatter);
		    	
		    } catch (DateTimeParseException e) {
		    	displayMessage("Please enter a valid date of format: dd/mm/yyyy");
		    }
	    }
	}
	
	/**
	 * This method reads in a duration in minutes
	 * @param message the prompt for the input
	 * @return the read duration
	 */
	public static Duration readDuration(String message) {
		int durationMinutes = readInt(message);
		return Duration.ofMinutes(durationMinutes);
	}
	
	/**
	 * This method prompts the user to press enter to continue
	 */
	public static void pressEnterToContinue() {
		displayMessage("Press enter to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
