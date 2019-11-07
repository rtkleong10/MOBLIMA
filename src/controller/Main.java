package controller;

import view.LoginView;

public class Main {
	public static void main(String[] args) {
		AppManager.initialize();
		
		LoginView loginView = new LoginView();
		loginView.start();
		
		AppManager.update();
	}
}
