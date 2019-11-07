package controller;

public class AppManager {
	private AppManager() {}

	public static void initialize() {
		AccountManager.initialise();
		CineplexManager.initialise();
	}
	
	public static void update() {
		AccountManager.update();
		CineplexManager.update();
	}
}
