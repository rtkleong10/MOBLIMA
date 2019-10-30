package Model;

public class ReviewRating {
	private static String review;
	private static int rating;
	
	
	public ReviewRating( String review , int rating)
	{this.review=review;
	 this.rating=rating;
		
		
	} 
	
	public static String getReview()
	{return review;
		
		
	}
	
	public static int getRating()
	{ return rating;
		
		
	}
	
	

}
