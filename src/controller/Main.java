package controller;

import model.DataManager;

public class Main {
	public static void main(String[] args) {
		double p = -3.4;
		System.out.println(p +2);
		DataManager.load();
		
		NavigationController.load(new UserController());
		
		DataManager.update();
	}
}
