package View;

import Model.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.*;
import java.util.stream.Collectors;
import View.*;
import Controller.*;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class ShowTimeView {
	
	
	public static void main(String [] args) {
		
	}
	
	public static void displayShowTime (List <ShowTime> s) {
		Map<Movie, List <ShowTime>> byMovie = s.stream()
				.collect(Collectors.groupingBy(ShowTime::getMovie));    
	
	
	for (Map.Entry<Movie, List <ShowTime>> entry: byMovie.entrySet()) {
		 System.out.println(entry.getKey().getTitle() +"\t");
		 List <ShowTime> shows= entry.getValue();
		 
		 Map<LocalDate,  List <ShowTime>> byDate = shows.stream()
					.collect(Collectors.groupingBy(ShowTime::getDate)); 
		 
		 for (Map.Entry<LocalDate,  List <ShowTime>> e: byDate.entrySet()) {
			 
			 
			 System.out.print(e.getKey()+"\t");
			 for (int i =0; i< e.getValue().size(); i++) {
				 System.out.print(e.getValue().get(i).getStartTime().toLocalTime()+"   ");
			 }
			 System.out.println("");
		 }
		 System.out.println("");
	}
	}
		
}
