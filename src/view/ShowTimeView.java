package view;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.*;
import java.util.stream.Collectors;

import controller.*;
import model.*;
import view.*;
import java.util.*;

public class ShowTimeView {
	
	
	public static void main(String [] args) {
		DataManager.load();
		ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		displayAllShowTimes(cineplexList);
	}
	/**
	 * Displays all ShowTimes from all Cineplexes
	 * @param cineplexes
	 */
	public static void displayAllShowTimes(List <Cineplex> cineplexes)
	{
		
		for (int i=0; i<cineplexes.size(); i++) {
			List <ShowTime> allShowTime = new ArrayList <>();
			System.out.println("   ===="+cineplexes.get(i).getName()+"===");
			allShowTime = cineplexes.get(i).getShowTimes();
			ShowTimeView.displayShowTime(allShowTime);
			}
			
	}
	
	/**
	 * Displays a list of showtimes
	 * @param s
	 */
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
