package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import Controller.*;
import View.*;
import Controller.CineplexManager;


public class ShowTimeGenerator {
	
	public static final int MAX_SHOWTIMES = 3; //max showtime per day = 4
	public static final int MAX_SHOWDAYS = 3;	
	
	public static  void main (String []args) {
		//Cinema Generator
			boolean layout1[][]=new boolean[11][19];
	        for(int i=0;i<11;i++)
			{
				for(int j=0;j<19;j++)
				{
					if(j==4||j==14|i==6)
						layout1[i][j]=false;
				else
					layout1[i][j]=true;
				}
			}
			boolean layout2[][]=new boolean[11][19];
			for(int i=0;i<11;i++)
			{
				for(int j=0;j<19;j++)
				{
				if(j==4||j==14||i==3)
				layout2[i][j]=false;
				else
				layout2[i][j]=true;
				}
			}
			layout2[0][1]=false;
			layout2[1][0]=false;
			layout2[0][0]=false;
			layout2[0][18]=false;
			layout2[1][18]=false;
			layout2[0][17]=false;
			layout2[10][1]=false;
			layout2[10][0]=false;
			layout2[9][0]=false;
			layout2[10][18]=false;
			layout2[9][18]=false;
			layout2[10][17]=false;
			boolean layout[][]=new boolean[11][19];
			for(int i=0;i<11;i++)
			{
				for(int j=0;j<19;j++)
				{
					layout[i][j]=true;
					if(j==6||j==12)
						layout[i][j]=false;
			    
				}
			}
			int count=4;
			for(int i=0;i<4;i++)
			{
				for (int j=0;j<count;j++)
				{
					layout[i][j]=false;
					layout[i][18-j]=false;
				}
				count--;
			}
	Cineplex[] cineplexes= new Cineplex[3];
	String[] cinplexNames= {"Golden Village","Cathay Cineplex", "Shaw Theatres"};
	String[] cinName= {"Screen 1", "Screen 2", "Screen 3"};
	for (int i = 0; i < cinplexNames.length; i++) {
	     cineplexes[i]=new Cineplex(cinplexNames[i]);
	     cineplexes[i].createCinema(cinName[0],layout, CinemaClass.NORMAL);
	     cineplexes[i].createCinema(cinName[1],layout1, CinemaClass.NORMAL);
	     cineplexes[i].createCinema(cinName[2],layout2, CinemaClass.PLATINUM_MOVIE_SUITE);
	 }
	
	// Movies generator
	int [] minute = {0,0,15,30,0,45} ;
	int [] hour = {9, 10, 11, 12, 13, 14, 15, 16, 17};
	LocalDateTime schedule ;
	ShowTime show;
	int n =0;
	ArrayList<Movie> fullMovieList = new ArrayList <Movie>();
	String[][] cast = {
			{"Daniel Radcliffe", "Emma Watson", "Rupert Grint"},
			{"Robert Downey Jr", "Mark Ruffalo", "Scarlett Johansson"},
			{"Harrison Ford", "Daisy Ridley", "Adam Driver"},
			{"Jon Heder", "Tina Majorino", "Jon Gries"},
			{"Will Arnett", "Lou Romano", "Patton Oswalt"},
			{"Al Pacino", "James Caan", "Diane Keaton"},
			{"Shakira", "Idris Elba",  "Jenny Slate"},
			{"Ward Horton", "Annabelle Walis", "Gabriel Bateman"}
		};
	 Movie movieList[] = {
			new Movie("Harry Potter and the Goblet of Fire", "This movie sees Harry, Ron and Hermione returning for their 4th year in Hogwarts. There is an upcoming tournament between 3 schools where one partipant must from each school must compete in a dangerous quest.", "Mike Newell", cast[0], ReleaseRating.PG, MovieType.BLOCKBUSTER, Duration.ofMinutes(120)),
			new Movie("Avengers: Endgame", "The final movie of the Avengers Franchise where the remaining Avengers must figure out a way to defeat Thanos, and save the world and their other superhero friends", "Anthony Russo", cast[1], ReleaseRating.PG13, MovieType.BLOCKBUSTER, Duration.ofMinutes(90)),
			new Movie("Starwars: The Force Awakens", "A new order threatens to destroy the New Republic. Finn,  Rey and Poe,  backed by the Resistance and the Republic,  must find a way to stop them and find Luke,  the last surviving Jedi.", "J.J. Abrahams", cast[2], ReleaseRating.PG13, MovieType._3D, Duration.ofMinutes(105)),
			new Movie("Napolean Dynamite", "Napoleon,  a socially awkward teenager,  gets caught up in his dysfunctional family's misadventures while trying to help a friend win the class presidency.", "Jared Hess", cast[3], ReleaseRating.PG13, MovieType.REGULAR, Duration.ofMinutes(135)),
			new Movie("Ratatouille", "Remy,  a rat,  aspires to become a renowned French chef. He doesn't realise that people despise rodents and will never enjoy a meal cooked by him.", "Brad Bird", cast[4], ReleaseRating.G, MovieType.REGULAR, Duration.ofMinutes(150)),
			new Movie("The Godfather", "Don Vito Corleone, head of a mafia family, decides to handover his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.", "Francis Coppola", cast[5], ReleaseRating.NC16, MovieType.REGULAR, Duration.ofMinutes(135)),
			new Movie("Zootopia", "Judy Hopps and Nick Wilde team up to uncover the mystery behind the fourteen missing predators. They end up finding out that the conspiracy is larger than it seems.", "Rich Moore", cast[6], ReleaseRating.G, MovieType.REGULAR, Duration.ofMinutes(165)),
			new Movie("Annabelle", "John and Mia are attacked by a couple, who are worshippers of Satan. However, before the cops kill them, the couple use a doll as a conduit to make John and Mia's life miserable.", "John Leonetti", cast[7], ReleaseRating.NC16, MovieType.BLOCKBUSTER, Duration.ofMinutes(125))
	};
	for (int i=0; i<movieList.length; i++) 
		fullMovieList.add(movieList[i]);
	
	//Showtime generator
	ArrayList<ShowTime> showTimeList = new ArrayList <ShowTime>();
	
	for(int i =0; i< cineplexes.length; i++ ) {
		ArrayList <Cinema> cinemas = new ArrayList <Cinema>();
		cinemas = cineplexes[i].getCinemas();
		for(int j=0; j< cinemas.size() ; j++ ) {
			for (int k=0 ; k< MAX_SHOWDAYS; k++) {
				for(int l=0; l<MAX_SHOWTIMES; l++) {
					schedule = LocalDateTime.of(2019, 12, k+1, hour[n%(hour.length)],minute[n%(minute.length)]);
					cinemas.get(j).createShowTime(cinemas.get(j).getLayout(), schedule, fullMovieList.get(n%(fullMovieList.size())));
					n++;
				}
		
			}
		}
	}
	
	/*below is just for printing out the code 
	
	MovieView mo = new MovieView();
	for(int i =0; i< cineplexes.length; i++ ) {
		ArrayList <Cinema> cinemas = new ArrayList <Cinema>();
		cinemas = cineplexes[i].getCinemas();
		System.out.println("Cineplex "+i);
		for(int j=0; j< cinemas.size() ; j++ ) {
			s = cinemas.get(j).getShowTimes(); {
				System.out.println("Cinema "+j);
				for(int l=0; l<s.size(); l++) {
					System.out.println("Showtime " +l);
					Movie m = s.get(l).getMovie();
					
					mo.printMovieDetails(m);
				}
			}
		}
	}*/
	}
}
