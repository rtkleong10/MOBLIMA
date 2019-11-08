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
	
	
	public static void main (String args[]) {
		
		int choice;
		Scanner sc = new Scanner (System.in);
		System.out.println("Select Cineplex: ");
		System.out.println("1) Golden Village\n"
				+ 			"2) Cathay Cineplex\n"
				+			"3) Shaw Theater\n");
		System.out.print("Option: ");
		choice = sc.nextInt();
		switch(choice) {
		case 1: // read in Golden Village 
			//get showtime
		}
		
		
		//dummy data
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
			
		//moviegoer
		MovieGoer[] movieGoers = new MovieGoer[1];
		for (int i = 0; i < 1; i++) {
		    movieGoers[i] = new MovieGoer("nanyoci", "Sally",null,null,null,null);
		}
		
		String movieShowTime = "MovieShowTimes";
		//writeSerializedObject(movieShowTime, shows);
		
		List <ShowTime> readShow;
		readShow =readSerializedObject(movieShowTime);
		
		//booking begins
		boolean done = false;
		boolean[][] book3;
		
		/*
		 * New method: checkfull() 
		 * check if showtime  is full before booking
		 
		readShow.get(0).createBooking("123456",movieGoers[0], layout, 5.0);
		displaySeat(readShow.get(0));
		if(readShow.get(0).checkFull())
			System.out.println("FULLY BOOKED");
		else book3=getSeat(readShow.get(0));
		
		*/
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
