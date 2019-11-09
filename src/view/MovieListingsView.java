package view;


import java.time.Duration;
import java.util.ArrayList;

import controller.DataGenerator;
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
		Object movie;
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
				System.out.println("Enter movie duration:");
				long due;
				due=sc.nextLong();
				Duration d = Duration.ofHours(due);
				Movie m= new Movie(moviename,synopsis,directorname,castnames, ReleaseRating.values()[movierating-1], MovieType.values()[movietype-1],d );
				fullMovieList.add(m);
				DataManager.update();
				break;
				
			case 2:
				int x; 
				System.out.println("Enter a movie you want to update");
				String moviename2 = sc.nextLine();
		        Movie moviee =null;
				for (Movie movie1:fullMovieList)
				    {x=moviename2.compareTo(movie1.getTitle());
				    if(x==0)
				    	{moviee=movie1;
				    	break;}
				    }
			   if(moviee==null)
			   { System.out.println("Enter a valid movie title");
				   
			   }
				
				    	
				int option2=getMenuOption( "What would you want to update",
						 "Title",
						 "Synopsis",
						 "director",
						 "Movie type",
						 "Movie rating",
						 "Cast members",
						 "Movie duration",
						 "Exit");
				
				switch(option2) {
			
				
				    	
					case 1:
						System.out.println("Enter updated title");
						String movie2=sc.nextLine();
				        moviee.setTitle(movie2);
				        break;
							  
				    case 2:
						System.out.println("Enter updated synopsis");
						String newsynopsis = sc.nextLine();
						moviee.setSynopsis(newsynopsis);
						break;
					case 3:
						System.out.println("Enter updated director");
						String newdirector =sc.nextLine();
						moviee.setDirector(newdirector);
						break;
					case 4:
						System.out.println("Enter updated movie type (Normal/Blockbuster/3D)");
						String newmtype =sc.nextLine();
						moviee.setDirector(newmtype);
						break;
					case 5:		
						System.out.println("Enter updated movie rating (G,PG,PG13,NC16,M18,R21)");
						String newmrating =sc.nextLine();
						moviee.setDirector(newmrating);
						break;
					case 6:
						System.out.println("Enter updated no. cast members");
						int n=sc.nextInt();
						String[] newcast = new String[n];
						System.out.println("Enter updated cast member names");
						for(int i=0;i<n;i++)
					    newcast[i] =sc.nextLine();
						moviee.setCast(newcast);
						break;
					case 7:
						System.out.println("Enter updated movie duration");
						long due1;
						due1=sc.nextLong();
						Duration newd = Duration.ofHours(due1);
						moviee.setDuration(newd);
						break;
						
							
				}		
				
				break;
				
			case 3:
				break;
				
			case 4:
				exit();
		}
	}
	
	

}