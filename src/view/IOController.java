package view;

import java.util.Scanner;

@SuppressWarnings("resource")
public class IOController {
	public static void displayLine(int length) {
		for (int i = 0; i < length; i++)
			System.out.print("-");
		
		System.out.println();
	}
	
	public static void displayMessageInline(String message) {
		System.out.print(message);
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	public static String readLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static String readLine(String message) {
		displayMessageInline(message);
		return readLine();
	}
	
	public static int readInt() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static int readInt(String message) {
		displayMessageInline(message);
		return readInt();
	}
	
	public static boolean readBoolean(String yesString, String noString) {
		while (true) {
			String input = readLine();
			
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
	
	public static void pressEnterToContinue() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Press enter to continue...");
		sc.nextLine();
	}
}
