package view;

import java.util.ArrayList;
import controller.DataManager;
import controller.DataStore;
import model.Movie;
import model.ReleaseRating;
import model.MovieType;
public class MovieListingsView extends View {
	public void start() {
		displayMenu();		
	}
	private void displayMenu() {
		int option = getMenuOption(
			"What would you like to do?",
			"Add a Movie",
			"Update a Movie",
			"Remove a Movie",
			"Exit"
		);
		DataManager.initialise();
		DataStore dataStore = DataManager.getDataStore();
		ArrayList<Movie> fullMovieList = dataStore.getMovieList();
		switch (option) {
			case 1:
				System.out.println("Enter a movie to add: ");
				String moviename=sc.nextLine();
				System.out.println("Enter movie synopsis: ");
				String synopsis=sc.nextLine();
				System.out.println("Enter name of director: ");
				String directorname=sc.nextLine();
				int movietype= getMenuOption("Enter movie type: ","Normal","Blockbuster","3D");
				int movierating= getMenuOption("Enter movie rating: ","G","PG","PG13","NC16","M18","R21");
				System.out.println("Enter number of cast: ");
				int castsize=sc.nextInt();
				String[] castnames= new String[castsize];
				System.out.println("Enter names of all cast members: ");
				String dummy=sc.nextLine();
				for (int i = 0; i < castsize; i++)
					castnames[i]=sc.nextLine();
				Movie m= new Movie(moviename,synopsis,directorname,castnames, ReleaseRating.values()[movierating-1], MovieType.values()[movietype-1]);
				fullMovieList.add(m);
				DataManager.update();
				break;
				
			case 2:
				
				
				break;
			case 3:
				break;
				
			case 4:
				exit();
		}
	}
	
	

}
