package view;

import model.Cineplex;
import model.Movie;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.DataManager;
import controller.DataStore;
import controller.ShowTimeManager;
import model.Cinema;
import model.ShowTime;
public class CinemaShowtimesView extends View {
	public void start()
	{
		displayMenu();
	}
	public void displayMenu() {
		DataManager.initialise();
		DataStore dataStore = DataManager.getDataStore();
		ArrayList<Movie> fullMovieList = dataStore.getMovieList();
		ArrayList<Cineplex> cineplexList = dataStore.getCineplexList();
		while (1) {
		int option=getMenuOption("What would you like to do?",
				"Add Showtimes",
				"Update Showtimes",
				"Remove Showtimes",
				"Exit");
		System.out.println("Enter cineplex: ");
		String cineplexName=sc.nextLine();
		Cineplex c;
		for(Cineplex cin:cineplexList)
		{
			if(cineplexName.compareTo(cin.getName())==0) {
				c=cin;
				break;
			}
		}
		if(c==null)
		{
			System.out.println("Cineplex does not exist!");
			continue;
		}
		System.out.println("Enter cinema code: ");
		String cinemaCode=sc.nextLine();
		ArrayList<Cinema> cinemas=c.getCinemas();
		Cinema cin;
		for(Cinema c1:cinemas)
		{
			if(cinemaCode.compareTo(c1.getCinemaCode())==0) {
				cin=c1;
				break;
		}
		}
		if(cin==null)
		{
		System.out.println("Cinema code not in cineplex!");
		continue;
		}
		
		switch(option)
		{
			case 1:
				ShowTimeManager.addShowTime(cin,fullMovieList);
				break;
				
			case 2:
				ShowTimeManager.updateShowTime(cin,fullMovieList);
				break;
			case 3:
				ShowTimeManager.deleteShowTime(cin);
				
				break;
			case 4:
			default:
				c
		}
		}
	}
	

}
