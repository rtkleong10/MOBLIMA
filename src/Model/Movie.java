package Model;


public class Movie {
	private String title;
	private String synopsis;
	private String director;
	private String[] cast;
	private double OverallRating ;
	
	public void calcOverallRating() 
	{ int s,n;
	  n=0;
	  s=0;
	  s+= ReviewRating.getRating();
	  n+=1;
	  OverallRating=s/n;
	
	}
   
	public double getOverallRating()
	{
		return OverallRating;
		
		
	}
	
	
	

}
