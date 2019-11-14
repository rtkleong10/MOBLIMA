package view;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cineplex;
import model.DataManager;
import model.Movie;
import model.ShowTime;

public class ShowTimeView {
	
	public static void displayAllShowTimes() {
		List<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
		
		for (Cineplex cineplex: cineplexList) {
			IOController.displayTitle(cineplex.getName());
			List<ShowTime> showTimeList = cineplex.getShowTimes();
			
			// Group show times by their movies
			Map<Movie, List<ShowTime>> showTimesByMovie = showTimeList.stream().collect(Collectors.groupingBy(ShowTime::getMovie));    
			
			for (Map.Entry<Movie, List<ShowTime>> movieShowTimes: showTimesByMovie.entrySet()) {
				Movie movie = movieShowTimes.getKey();
				List<ShowTime> movieShowTimeList = movieShowTimes.getValue();
				
				IOController.displayMessage(movie.getTitle());
				
				Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getStartDateTime);
				movieShowTimeList.sort(dateComparator);
				
				for (ShowTime showTime: movieShowTimeList)
					IOController.displayMessage(showTime.getLabel());
				
				IOController.displayMessage("");
			}
		}
		
		IOController.pressEnterToContinue();
	}
}
