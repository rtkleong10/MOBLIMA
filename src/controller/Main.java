package controller;

import model.DataManager;

/**
 * This class is the main class of the application.
 */
public class Main {
	/**
	 * The main function that launches the application
	 * @param args unused
	 */
	public static void main(String[] args) {
		DataManager.load();
		
		NavigationController.load(new UserController());
		
		DataManager.update();
	}
}
