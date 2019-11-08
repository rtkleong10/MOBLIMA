package view;

import java.util.ArrayList;

import controller.DataManager;
import model.MovieGoer;
import model.Cineplex;

public class CineplexSelectView extends View {
	
	private MovieGoer movieGoer;
	
	public CineplexSelectView(MovieGoer movieGoer) {
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
		
		int option = getMenuOption(
			"Select a Cinema",
			cineplexStrings
		);
		
		if (option != size)
			load(new CineplexView(movieGoer, cineplexList.get(option)));
		else
			exit();
	}
}
