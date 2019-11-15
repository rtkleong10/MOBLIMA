package view;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		displayMessageInline(message);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static double readDouble(String message) {
		displayMessageInline(message);
		Scanner sc = new Scanner(System.in);
		return sc.nextDouble();
	}
	
	public static boolean readBoolean(String yesString, String noString) {
		while (true) {
			String input = readLine("");
			
			if (input.equals(yesString))
				return true;
			else if (input.equals(noString))
				return false;
			
			displayMessage("Error: Invalid input (input should be " + yesString + " or " + noString + ")");
		}
	}
	
	public static boolean readBoolean(String message, String yesString, String noString) {
		displayMessageInline(message);
		return readBoolean(yesString, noString);
	}
	
	public static LocalDateTime readDateTime(String message) {
		String dateTimeString = readLine(message);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return LocalDateTime.parse(dateTimeString, formatter);
	}
	
	public static LocalDate readDate(String message) {
		String dateString = readLine(message);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateString, formatter);
	}
	
	public static Duration readDuration(String message) {
		int durationMinutes = IOController.readInt(message);
		return Duration.ofMinutes(durationMinutes);
	}
	
	public static void pressEnterToContinue() {
		displayMessage("Press enter to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
