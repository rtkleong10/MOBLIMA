package view;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("resource")
public class IOController {
	public static void displayMessageInline(String message) {
		System.out.print(message);
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	public static void displayTitle(String title) {
		displayMessage(title);
		
		String line = "";
		
		for (int i = 0; i < title.length(); i++)
			line += "-";
		
		displayMessage(line);
	}
	
	public static void displayMessageCentred(String message, int width) {
		String margin = "";
		for (int i = 0; i < (width - message.length()) / 2; i++)
			margin += " ";
		
		displayMessage(margin + message);
	}
	
	public static String readLine(String message) {
		displayMessageInline(message);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
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
	
	public static Duration readDuration(String message) {
		int durationMinutes = readInt(message);
		return Duration.ofMinutes(durationMinutes);
	}
	
	public static void pressEnterToContinue() {
		displayMessage("Press enter to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
