package view;

import java.util.Scanner;

@SuppressWarnings("resource")
public class IOController {	
	public static int getMenuOption(String title, String... options) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(title);
		System.out.println("———————————————————————");
		
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ": " + options[i]);
		}
		
		int option;
		
		while (true) {
			System.out.print("Option: ");
			option = sc.nextInt();
			
			if (option >= 1 && option <= options.length + 1)
				break;
			else
				System.out.println("Invalid option selected!");
		}
		
		return option;
	}
	
	public static String readLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static int readInt() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static void pressEnterToContinue() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Press enter to continue...");
		sc.nextLine();
	}
}
