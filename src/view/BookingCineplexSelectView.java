package view;

import java.util.ArrayList;

import controller.DataManager;
import model.Cineplex;
import model.MovieGoer;

public class BookingCineplexSelectView extends View {
	private MovieGoer movieGoer;
	
	public BookingCineplexSelectView(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
	public void start() {
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		int size = cineplexList.size();
		String[] cineplexStrings = new String[size + 1];
		
		for (int i = 0; i < size; i++) {
			cineplexStrings[i] = cineplexList.get(i).getName();
		}
		
		cineplexStrings[size] = "Exit";
		
		int option = IOController.getMenuOption(
			"Select a cineplex",
			cineplexStrings
		);
		
		option--;
		
		if (option != size)
			load(new BookingMovieSelectView(movieGoer, cineplexList.get(option)));
		else
			exit();
	}
}
