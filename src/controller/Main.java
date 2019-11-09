package controller;

import java.util.Scanner;

import view.AppView;

public class Main {
	protected static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		DataManager.load();
		
		AppView appView = new AppView();
		appView.start();
		
		DataManager.update();
	}
}
