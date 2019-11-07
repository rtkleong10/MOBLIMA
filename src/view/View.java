package view;

import java.util.Scanner;

public abstract class View {
	protected Scanner sc = new Scanner(System.in);
	
	public View previousView;
	
	public abstract void start();
	
	protected void exit() {
		if (previousView == null) { // Exit if it doesn't have previous view
			System.exit(1);
		
		} else {
			System.out.println("");
			previousView.start();
		}
	}
	
	protected void load(View nextView) {
		System.out.println("");
		nextView.previousView = this;
		nextView.start();
	}
	
	protected int getMenuOption(String title, String... options) {
		System.out.println(title);
		
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
}