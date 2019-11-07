package Controller;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import Model.ShowTime;
import Model.AgeGroup;
import Model.Booking;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieType;
import Model.ReleaseRating;
import Model.SeatStatus;
import java.time.Month;
import Model.MovieGoer;
import java.io.PrintStream;
import Controller.CineplexManager;
import Model.Cineplex;
import Model.Cinema;

public class BookingApp {
	public static final int MAX_SHOWTIMES = 4; //max showtime per day = 4
	public static final int MAX_SHOWDAYS = 7;
	
	public static void main (String args[]) {
		
		//8 casts
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
		//8 movies
		Movie movieList[] = {
				new Movie("Harry Potter and the Goblet of Fire", "This movie sees Harry, Ron and Hermione returning for their 4th year in Hogwarts. There is an upcoming tournament between 3 schools where one partipant must from each school must compete in a dangerous quest.", "Mike Newell", cast[0], ReleaseRating.PG, MovieType.BLOCKBUSTER, Duration.ofMinutes(120)),
				new Movie("Avengers: Endgame", "The final movie of the Avengers Franchise where the remaining Avengers must figure out a way to defeat Thanos, and save the world and their other superhero friends", "Anthony Russo", cast[1], ReleaseRating.PG13, MovieType.BLOCKBUSTER, Duration.ofMinutes(150)),
				new Movie("Starwars: The Force Awakens", "A new order threatens to destroy the New Republic. Finn,  Rey and Poe,  backed by the Resistance and the Republic,  must find a way to stop them and find Luke,  the last surviving Jedi.", "J.J. Abrahams", cast[2], ReleaseRating.PG13, MovieType._3D, Duration.ofMinutes(165)),
				new Movie("Napolean Dynamite", "Napoleon,  a socially awkward teenager,  gets caught up in his dysfunctional family's misadventures while trying to help a friend win the class presidency.", "Jared Hess", cast[3], ReleaseRating.PG13, MovieType.REGULAR, Duration.ofMinutes(90)),
				new Movie("Ratatouille", "Remy,  a rat,  aspires to become a renowned French chef. He doesn't realise that people despise rodents and will never enjoy a meal cooked by him.", "Brad Bird", cast[4], ReleaseRating.G, MovieType.REGULAR, Duration.ofMinutes(105)),
				new Movie("The Godfather", "Don Vito Corleone, head of a mafia family, decides to handover his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.", "Francis Coppola", cast[5], ReleaseRating.NC16, MovieType.REGULAR, Duration.ofMinutes(90)),
				new Movie("Zootopia", "Judy Hopps and Nick Wilde team up to uncover the mystery behind the fourteen missing predators. They end up finding out that the conspiracy is larger than it seems.", "Rich Moore", cast[6], ReleaseRating.G, MovieType.REGULAR,Duration.ofMinutes(75)),
				new Movie("Annabelle", "John and Mia are attacked by a couple, who are worshippers of Satan. However, before the cops kill them, the couple use a doll as a conduit to make John and Mia's life miserable.", "John Leonetti", cast[7], ReleaseRating.NC16, MovieType.BLOCKBUSTER, Duration.ofMinutes(140))
		};
		
		boolean layout[][]=new boolean[11][19];
        for(int i=0;i<11;i++){
			
			for(int j=0;j<19;j++)
			{
			if(j==4||j==14||i==6)
			layout[i][j]=false;
			
			else
			layout[i][j]=true;
			}
			
        }
        
        
		int [] minute = {0,0,15,30,0,45} ;
		int [] hour = {9, 10, 11, 12, 13, 14, 15, 16, 17};
		LocalDateTime schedule ;
		ShowTime show;
		ArrayList <ShowTime>shows = new ArrayList <ShowTime>();
		//duration cannot be float??
		ArrayList <Cineplex> cineplex = new ArrayList <Cineplex>();
		cineplex = CineplexManager.getCineplexList();
		ArrayList <Cinema> cinema = new ArrayList <Cinema>();
		
		for (int i=0 ; i< MAX_SHOWDAYS; i++) {
			for(int j =0; j< cineplex.size(); j++ ) {
				cinema = cineplex.get(0).getCinemas();
			
			for(int k=0; k<cinema.size() ; k++ ) {
				schedule = LocalDateTime.of(2019, 12, i+1, hour[i%(hour.length)],minute[i%(minute.length)]);
				show= new ShowTime( layout, schedule , movie[i]);
				shows.add(show);
				}
			}
		}
		//moviegoer
		MovieGoer[] movieGoers = new MovieGoer[3];
		String[] names = {"Bob", "Joe", "Sally"};
		for (int i = 0; i < names.length; i++) {
		    movieGoers[i] = new MovieGoer("nanyoci", names[i],null,null,null,null);
		}
		
		
		
		
		String movieShowTime = "MovieShowTimes";
		writeSerializedObject(movieShowTime, shows);
		
		List <ShowTime> readShow;
		readShow =readSerializedObject(movieShowTime);
		
		boolean done = false;
		boolean[][] book3;
		displaySeat(readShow.get(0));
		do {
			book3=getSeat(readShow.get(0));
			
			if(readShow.get(0).checkAvail( book3)) {
				readShow.get(0).createBooking("123456",movieGoers[0], book3, 5.0);
				System.out.println("Transaction ID: " +"123456"+" successful");
				done = true;
			}
			else {
				System.out.println("Seat is unavailable");
			}
		}while(!done);
		
		displaySeat(readShow.get(0));
		
	}
	
	public static boolean[][] getSeat (ShowTime show){
		boolean[][] layout = show.getLayout();
		boolean[][] selectedSeat = new boolean[layout.length][];
		for(int i=0; i<layout.length; i++) {
			selectedSeat[i] = new boolean[layout[i].length];
			for(int j=0 ; j<layout[i].length; j++) {
				selectedSeat[i][j] = false;
			}
		}
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		System.out.println("Please enter seat no. :");
			String in = sc.nextLine();
			String[] input = in.split("\\s+");
			for (int i=0; i<input.length; i++) {
				row = input[i].charAt(0)-'A';
				col = Integer.parseInt(input[i].substring(1) );
				selectedSeat[row][col-1] = true;
		}	
		return selectedSeat;
	}
	
	public static  void displaySeat (ShowTime show) {
		char row= 'A';
		int col =1;
		SeatStatus[][] availSeat = show.getSeatAvailabilities();
		
		
		for (int i =0; i< (5*(availSeat[0].length)/2)-2; i++)
			System.out.print(" ");
		System.out.println("SCREEN");
		
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		System.out.print("  ");
		
		for (int i =0; i< (availSeat[0].length); i++)
		{
			
				if(i<10)
					System.out.print("  "+col+"  ");	
				else
					System.out.print(" "+col+"  ");	
			
			col++;
		}
		System.out.println();	
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		
		System.out.println();
		for (SeatStatus[] i : availSeat)
		{
			System.out.print(row+" " );
			
		   for (SeatStatus j : i)
		   {
		        if (j == SeatStatus.TAKEN) 
		   		System.out.print( "[ x ]");
		   	else if(j== SeatStatus.EMPTY)
		   		System.out.print( "[   ]");
		   	else if(j== SeatStatus.NO_SEAT)
		   		System.out.print( "     ");
		   }
				System.out.print(" "+row );
		   		row++;
		   System.out.println();
		   if(row == availSeat.length) {
			   System.out.print(" ");
		   }
		}
		
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		
		col =1;
		System.out.print("  ");
		
		for (int i =0; i< (availSeat[0].length); i++)
		{
			
				if(i<10)
					System.out.print("  "+col+"  ");	
				else
					System.out.print(" "+col+"  ");	
			
			col++;
		}
		
		
		System.out.println();	
		for (int i =0; i< 5*(availSeat[0].length)+4; i++)
			System.out.print("-");
		System.out.println();
		for (int i =0; i< (5*(availSeat[0].length)/2)-3; i++)
			System.out.print(" ");
		System.out.println("ENTRANCE");
		
		
	}
	public static List readSerializedObject(String filename) {
		ArrayList <ShowTime>showList = new ArrayList <ShowTime>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			showList = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		finally {
		//print out the size
		System.out.println("Details Size: " + showList.size());
		System.out.println();
		}
		return showList;
	}

	public static void writeSerializedObject(String filename, ArrayList <ShowTime> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
