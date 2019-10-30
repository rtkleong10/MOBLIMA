package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class BookingApp {
	public static void main (String args[]) {
		String movieShowTime ="MovieShowTime";
		//creating some showtimes
		int[][] layout1 = {{-1, -1, 1, 1, 1, 1},
						  {-1, -1, 1, 1, 1, 1}};	
		
		String showName ="Spider Man";
		ShowTime show1 = new ShowTime(showName, layout1 );
		
		int[][] book1 = {{-1, -1, 1, 2, 2, 2,},
  						{-1, -1, 1, 1, 1, 1}};
		show1.createBooking(book1,"1234", 31.5);
		
		int[][] book2 = {{-1, -1, 1, 1, 1, 1,},
						{-1, -1, 1, 1, 1, 2}};
		show1.createBooking(book2,"1235", 12.5);
		
		ArrayList <ShowTime>showTimeList = new ArrayList <ShowTime>();
		
		showTimeList.add(show1);
		
		writeSerializedObject(movieShowTime, showTimeList);
		
		List <ShowTime> readShow;
		readShow =readSerializedObject(movieShowTime);
		
		int[][] availSeat = readShow.get(0).getSeatAvailabilities();
		for (int[] i : availSeat)
		{
		   for (int j : i)
		   {
		       //System.out.print(j + " ");
		        if (j == 0) 
		   		System.out.print( "☒ ");
		   	else if(j==1)
		   		System.out.print( "☐ ");
		   	else 
		   		System.out.print( "■ ");
		   }
		   System.out.println();
		}
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
