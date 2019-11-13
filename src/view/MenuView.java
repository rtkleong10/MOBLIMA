package view;

import java.util.Scanner;

import model.LabelledItem;

@SuppressWarnings("resource")
public class MenuView {
	
	public static int getMenuOption(String title, String... options) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println(title);
		IOController.displayLine(title.length());
		
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
		
		System.out.println();
		
		return option;
	}
	
	public static <T extends LabelledItem> T getLabelledItem(String title, T[] labelledItems) {
		String[] options = new String[labelledItems.length];
		
		for (int i = 0; i < labelledItems.length; i++)
			options[i] = labelledItems[i].getLabel();
		
		int option = getMenuOption(title, options);
		
		return labelledItems[option - 1];
	}
}
