package controller;

import java.util.Scanner;

import model.AgeGroup;
import model.Booking;
import model.Movie;
import model.MovieGoer;
import model.MovieType;
import model.ReleaseRating;
import model.SeatStatus;
import model.ShowTime;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.time.Month;
import java.io.PrintStream;

public class BookingApp {
	public static final int MAX_SHOWTIMES = 1;
	
	public static void main (String args[]) {
		
		//dummy movie
		String []movie1Cast = new String [] {"Tom"};
		Movie movie1 = new Movie("Spider-Man Far From Home", "Peter Parker's life", "Jon Watt", 
				movie1Cast, ReleaseRating.PG13,
				MovieType.REGULAR);
		//dummy cinema
		boolean[][] layout1 = {{false, true, true, false, true, true,false},
						  		{false, true,true, false, true, true, false},
						  		{false, true, true, false, true, true, false},
						  		{false, true, true, false, true, true, false},
						  		{false, true, true, false, true, true, false}};
		
		boolean[][] layout2 = {{false, true, true, false},
		  		{false, true,true, false},
		  		{false, true, true, false},
		  		{false, true, true, false},
		  		{false, true, true, false}};
		//generate showtimes schedule
		int [] minute = {0,0,15,30,0,45} ;
		int [] hour = {9, 10, 11, 12, 13, 14, 15, 16, 17};
		LocalDateTime schedule ;
		ShowTime show;
		ArrayList <ShowTime>shows = new ArrayList <ShowTime>();
		//duration cannot be float??
		for (int i=0 ; i< MAX_SHOWTIMES; i++) {
			schedule = LocalDateTime.of(2019, 12, i+1, hour[i%(hour.length)],minute[i%(minute.length)]);
			show= new ShowTime( layout1, schedule ,Duration.ofMinutes(120), movie1 );
			shows.add(show);
		}
		//moviegoer
		MovieGoer[] movieGoers = new MovieGoer[3];
		String[] names = {"Bob", "Joe", "Sally"};
		for (int i = 0; i < names.length; i++) {
		    movieGoers[i] = new MovieGoer("nanyoci", names[i],null,null,null);
		}
		//booking data
		boolean[][] book1 = {{false, false,false, false, false,false, false},
		  		{false, false,false, false, false,false, false},
		  		{false, false,false, false, false,false, false},
		  		{false, false,false, false, false,false, false},
		  		{false, false,false, false, true,true, false}};
		boolean[][] book2 = {{false, true, true, false},
		  		{false, true,true, false},
		  		{false, true, true, false},
		  		{false, true, true, false},
		  		{false, true, true, false}};
		
		String movieShowTime = "MovieShowTimes";
		writeSerializedObject(movieShowTime, shows);
		
		List <ShowTime> readShow;
		readShow =readSerializedObject(movieShowTime);
		
		boolean done = false;
		boolean[][] book3;
		displaySeat(readShow.get(0));
		do {
			book3=getSeat(readShow.get(0));
			for(int j=0; j<book3.length; j++) {
				for(int k=0 ; k<book3[j].length; k++) {
					System.out.print(book3[j][k] + " ");
				}
				System.out.println("\n");
			}
			if(readShow.get(0).checkAvail( book3)) {
				readShow.get(0).createBooking("123456",movieGoers[0], book3, 5.0);
				System.out.println("Transaction ID: " +"123456"+" successful");
				done = true;
			}
			else {
				System.out.println("Seat is taken");
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
				selectedSeat[row][col] = true;
		}
			
		return selectedSeat;
	}
	
	
	public static  void displaySeat (ShowTime show) {
		char row= 'A';
		int col =1;
		SeatStatus[][] availSeat = show.getSeatAvailabilities();
		
		for (int i =0; i< (3*(availSeat[0].length)/2)-2; i++)
			System.out.print(" ");
		System.out.println("SCREEN");
		
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		System.out.println();
		System.out.print(" ");
		
		for (int i =0; i< (availSeat[0].length); i++)
		{
			if(availSeat[0][i] != SeatStatus.NO_SEAT) {
				System.out.print(" "+col+" ");	
				col++;
			}
			else if(availSeat[0][i] == SeatStatus.NO_SEAT) {
				System.out.print("   ");
			}
		}
		System.out.println();	
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		
		System.out.println();
		for (SeatStatus[] i : availSeat)
		{
			System.out.print(row );
			
		   for (SeatStatus j : i)
		   {
		        if (j == SeatStatus.TAKEN) 
		   		System.out.print( "[x]");
		   	else if(j== SeatStatus.EMPTY)
		   		System.out.print( "[ ]");
		   	else if(j== SeatStatus.NO_SEAT)
		   		System.out.print( "   ");
		   }
		   System.out.print(row);
		  row++;
		   System.out.println();
		   if(row == availSeat.length) {
			   System.out.print(" ");
			   //for(int k =0; k<=availSeat[0].length; k++)
		   }
		}
		
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		System.out.println();
		
		col =1;
		System.out.print(" ");
		for (int i =0; i< (availSeat[0].length); i++)
		{
			if(availSeat[0][i] != SeatStatus.NO_SEAT) {
				System.out.print(" "+col+" ");	
				col++;
			}
			else if(availSeat[0][i] == SeatStatus.NO_SEAT) {
				System.out.print("   ");
			}
		}
		System.out.println();	
		for (int i =0; i< 3*(availSeat[0].length)+2; i++)
			System.out.print("-");
		System.out.println();
		for (int i =0; i< (3*(availSeat[0].length)/2)-3; i++)
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
