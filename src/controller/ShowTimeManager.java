package controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.DataManager;
import controller.DataStore;
import model.Cinema;
import model.Cineplex;
import model.ShowTime;
import model.Movie;
public class ShowTimeManager {
	public void getData() {
		
	}
	
	public static void addShowTime(Cinema c, ArrayList<Movie> m) {
		ArrayList<ShowTime> st= c.getShowTimes();
		System.out.println("Current Showtimes");
		for(ShowTime s:st) {
			System.out.print("Movie: "+s.getMovie().getTitle()+" Start Time: " + s.getStartTime()+" Duration: "+s.getDuration());
		}
		System.out.println("Enter a showtime to add:");
		System.out.println("Year: ");
		int y=sc.nextInt();
		System.out.println("Month: ");
		int mo=sc.nextInt();
		System.out.println("Day: ");
		int d=sc.nextInt();
		System.out.println("Hour: ");
		int h=sc.nextInt();
		System.out.println("Minute: ");
		int min=sc.nextInt();
		LocalDateTime startTime=LocalDateTime.of(y, mo, d, h, min);
		System.out.println("Enter the movie: ");
		String movieName=sc.nextLine();
		Movie m1;
		for(Movie mov :m)
		{
			
			if(movieName.compareTo(mov.getTitle())==0) {
				m1=mov;
				break;
		}
		}
		if(m1 == null)
		{
		System.out.println("Invalid Movie Name!");
		return;
		}
		Duration durn=m1.getDuration();
		LocalDateTime endTime=startTime.plusMinutes(durn.toMinutes());
		ShowTime st2;
		boolean clash=false;
		for (ShowTime s: st)
		{
			if((s.getStartTime().compareTo(endTime)<0 && startTime.compareTo<0) ) {
				System.out.println("Failed to add showtime due to clash!");
				return;
			}
		c.createShowTime(startTime, m1);		
				
		}
	}
	public static void updateShowTime(Cinema c, ArrayList<Movie> m) {
		ArrayList<ShowTime> st= c.getShowTimes();
		System.out.println("Current Showtimes");
		for(ShowTime s:st) {
			System.out.print("Movie: "+s.getMovie().getTitle()+" Start Time: " + s.getStartTime()+" Duration: "+s.getDuration());
		}
		System.out.println("Enter a showtime to update:");
		System.out.println("Year: ");
		int y=sc.nextInt();
		System.out.println("Month: ");
		int mo=sc.nextInt();
		System.out.println("Day: ");
		int d=sc.nextInt();
		System.out.println("Hour: ");
		int h=sc.nextInt();
		System.out.println("Minute: ");
		int min=sc.nextInt();
		LocalDateTime start=LocalDateTime.of(y, mo, d, h, min);
		int i;
		for(i=0; i<st.size(); i++)
		{
			
			if(start.compareTo(st.get(i).getStartTime())==0) {
				break;
		}
		Movie m1;
		LocalDateTime startTime=start;
		System.out.println("What would you like to change?");
		System.out.println("1. Start Time\n2. Movie");
		int option=sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("Enter new showtime");
			System.out.println("Year: ");
			int yr=sc.nextInt();
			System.out.println("Month: ");
			int month=sc.nextInt();
			System.out.println("Day: ");
			int day=sc.nextInt();
			System.out.println("Hour: ");
			int hour=sc.nextInt();
			System.out.println("Minute: ");
			int minute=sc.nextInt();
			startTime=LocalDateTime.of(yr, month, day, hour, minute);
			m1=c.getShowTimes().get(i).getMovie();
			break;
		case 2:
			System.out.println("Enter the movie: ");
			String movieName=sc.nextLine();
			for(Movie mov :m)
			{
				
				if(movieName.compareTo(mov.getTitle())==0) {
					m1=mov;
					break;
			}
			}
			if(m1 == null)
			{
			System.out.println("Invalid Movie Name!");
			return;
			}
			break;
		default:
			System.out.println("Invalid Option Selected!");
		}
		
		Duration durn=m1.getDuration();
		LocalDateTime endTime=startTime.plusMinutes(durn.toMinutes());
		ShowTime st2;
		boolean clash=false;
		for (ShowTime s: st)
		{
			if((s.getStartTime().compareTo(endTime)<0 && startTime.compareTo<0) ) {
				System.out.println("Failed to update showtime due to clash!");
				break;
			}
		}
				
				
		}
	}
	
	public static void deleteShowTime(Cinema c) {
		ArrayList<ShowTime> st= c.getShowTimes();
		System.out.println("Current Showtimes");
		for(ShowTime s:st) {
			System.out.print("Movie: "+s.getMovie().getTitle()+" Start Time: " + s.getStartTime()+" Duration: "+s.getDuration());
		}
		System.out.println("Enter a showtime to delete:");
		System.out.println("Year: ");
		int y=sc.nextInt();
		System.out.println("Month: ");
		int mo=sc.nextInt();
		System.out.println("Day: ");
		int d=sc.nextInt();
		System.out.println("Hour: ");
		int h=sc.nextInt();
		System.out.println("Minute: ");
		int min=sc.nextInt();
		LocalDateTime startTime=LocalDateTime.of(y, mo, d, h, min);
	
		for(ShowTime s :st)
		{
			
			if(startTime.compareTo(s.getStartTime())==0) {
				c.removeShowTime(s);
				System.out.println("Showtime Deleted");
				return;
		}
		}
		System.out.println("Could not find showtime to delete");		
		}
	}


