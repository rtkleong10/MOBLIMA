package controller;

import view.AppView;

public class Main {
	public static void main(String[] args) {
		DataManager.load();
		
		AppView loginView = new AppView();
		loginView.start();
		
		DataManager.update();
	}
}
