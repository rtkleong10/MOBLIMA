package view;

import model.Cineplex;
import java.util.ArrayList;
import model.Cinema;
import model.ShowTime;
public class CinemaShowtimesView extends View {
	public void start()
	{
		displayMenu();
	}
	public void displayMenu() {
		int option=getMenuOption("What would you like to do?",
				"Add Showtimes",
				"Update Showtimes",
				"Remove Showtimes",
				"Exit");
		switch(option)
		{
			case 1:
				System.out.println("Enter cineplex: ");
				String cineplexName=sc.nextLine();
				
				int x;
				Cineplex c;
				for(Cineplex cin1:CineplexList)
				{
					x=cineplexName.compareTo(cin1.getName());
					if(x==0) {
						c=cin1;
						break;
					}
				}
				if(x != 0)
				{
					System.out.println("Cineplex does not exist!");
					break;
				}
				System.out.println("Enter cinema code: ");
				String cinemaName=sc.nextLine();
				ArrayList<Cinema> cins=c.getCinemas();
				Cinema s;
				for(Cinema c1:cins)
				{
					x=cinemaName.compareTo(c1.getCinemaCode());
					if(x==0) {
						s=c1;
						break;
				}
				}
				if(x != 0)
				{
				System.out.println("Cinema code not in cineplex!");
				}
				ArrayList<ShowTime> st= s.getShowTimes();
				System.out.println("Current Show")
			case 2:
				break;
			case 3:
			case 4:
			default:
				c
		}
	}
	

}
