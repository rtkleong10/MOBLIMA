package Controller;

import java.util.HashMap;

import Model.*;

public class Main {
	public static void main(String[] args) {
		CineplexManager.initialize();
		HashMap<String, MovieGoer> movieGoerList = CineplexManager.getMovieGoerList();
		movieGoerList.put("joe", new MovieGoer("johndoe", "Joe", 92312, "Bob@gmail.com", AgeGroup.ADULT, "password"));
		System.out.println(movieGoerList.get("joe").getName());
		CineplexManager.setMovieGoerList(movieGoerList);
		CineplexManager.update();
	}
}
