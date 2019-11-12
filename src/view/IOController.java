package view;

import java.util.Scanner;

@SuppressWarnings("resource")
public class IOController {	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	public static String readLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static String readLine(String message) {
		displayMessage(message);
		return readLine();
	}
	
	public static int readInt() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static int readInt(String message) {
		displayMessage(message);
		return readInt();
	}
	
	public static boolean readBoolean(String yesString, String noString) {
		String input = readLine();
		
		while (true) {
			if (input.equals(yesString))
				return true;
			else if (input.equals(noString))
				return false;
			
			displayMessage("Error: Invalid input (input should be " + yesString + " or " + noString + ")");
		}
	}
	
	public static boolean readBoolean(String message, String yesString, String noString) {
		displayMessage(message);
		return readBoolean(yesString, noString);
	}
	
	public static void pressEnterToContinue() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Press enter to continue...");
		sc.nextLine();
	}
}
