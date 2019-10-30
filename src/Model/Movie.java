package Model;


public class Movie {
	private String title;
	private String synopsis;
	private String director;
	private String[] cast;
	private static double OverallRating ;
	
	public static void calcOverallRating(int n) 
	{ 
	OverallRating= ReviewRating.getRating()/n; //n is the total number of people who reviewed the movie
	 										  // get n by incrementing it in main when a person reviews
	         
	
	}
   
	public double getOverallRating()
	{
		return OverallRating;
		
		
	}
	
	
	

}
