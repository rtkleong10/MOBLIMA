package controller;

import model.DataManager;

public class Main {
	public static void main(String[] args) {
		DataManager.load();
		
		NavigationController.load(new UserController());
		
		DataManager.update();
	}
}
