package controller;
import java.util.Scanner;

public class StaffManager {
	


	import model.CinemaStaff;
	import model.PricingScheme;
	import model.Cineplex;
	
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
import java.time.LocalDate;
import java.time.LocalDateTime;
	import java.util.*;
	import java.time.Month;
	import java.io.PrintStream;



		
  public static void main (String args[]) {
	  
      Scanner sc=new Scanner(System.in);
	  String username, password;
	  System.out.println("Enter login details");
	  System.out.println("Enter username and password");
	  username=sc.nextLine();
	  password=sc.nextLine();
	  
	  staff1=AccountManager.getCinemaStaff(username,password);
	  if(staff1==null)
		  System.out.println("Wrong password!");
	  else
	  { int c;
		System.out.println("What do you want to change?");
	    System.out.println("1.Prices \n2.Holidays");
	    c=sc.nextInt();
	    if(c==1)
	    {  int c1;
	      System.out.println("Which multiplier do you want to change?\n1.CinemaClass\n2.Age\n3.Movie\n4.date");
	      c1=sc.nextInt();
	      if(c1==1)
	      {  double a;
	    	 System.out.println("Enter cinemaclass number, and the new multiplier");
	         CinemaClass value = new CinemaClass.values()[scanner.nextInt()];
	         a=sc.nextDouble();
	    	  
	      
	    	PricingScheme.setCinemaMultiplier(value,a);
	    	
	    	
	    	
	    }
	      else if (c1==2) {
	    	  
	    	     double a;
		    	 System.out.println("Enter age group, and the new multiplier");
		         AgeGroup value = new AgeGroup.values()[scanner.nextInt()];
		         a=sc.nextDouble();
		    	  
		      
		    	PricingScheme.setAgeMultiplier(value,a);
		    	
	    	  
	    	  
	    	  
	    	  
	      }
	      else if (c1==3)
	      {  double a;
	    	 System.out.println("Enter age group, and the new multiplier");
	         MovieType value = new MovieType.values()[scanner.nextInt()];
	         a=sc.nextDouble();
	    	  
	      
	    	PricingScheme.setMovieMultiplier(value,a);
	    	
	    	  
	      }
	      else if(c1==4)
	      {  double a;
	    	 System.out.println("Enter age group, and the new multiplier");
	         DateType value = new DateType.values()[scanner.nextInt()];
	         a=sc.nextDouble();
	    	  
	      
	    	PricingScheme.setDateMultiplier(value,a);
	    	
	    	  
	    	  
	    	  
	      }
	      
	      else if (c==2)
	      {   String hname;
	          int month,day,c2;
	          System.out.println("Do you want to 1.Add holiday\n2.Remove holiday");
	          c2=sc.nextInt();
	             if(c2==1) {
	    	  System.out.println("Enter holiday Date (month,day), and holiday name");
	    	  month=sc.nextInt();
	    	  day=sc.nextInt();
	    	  hname=sc.nextLine();
	    	  HashMap<LocalDate,String> holidays = new HashMap<LocalDate, String>();
			  holidays.put(LocalDate.of(2019, month, day),hname);
			  
			  
	    	  
	          }
	             else if(c2==2) {
	            	 int month1,day1;
	            	 System.out.println("Enter holiday Date(month,day)");
	            	 month1=sc.nextInt();
	            	 day1=sc.nextInt();
	            	 PricingScheme obj= new PricingScheme();
	            	
	   			     
	   			    obj.removeHolidayDate(LocalDate.of(2019, month1, day1));
	            	 
	            	 
	            	 
	            	 
	            	 
	             }
	            	 
	             }
	       
	      
	      
	      
	    	
	      
	    	  
	      }
	    	  
	    	  
	      
	    
	  }
		  
		  
		  
	  }
	  
	  
	  
  }
			
			
			
			