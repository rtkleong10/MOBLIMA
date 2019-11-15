package controller;

import view.MenuView;
import view.ShowTimeEditView;
import view.ShowTimeView;

public class ShowTimeEditController implements Controller {

	@Override
	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"View movie showtimes",
				"Add a Show Time",
				"Update a Show Time",
				"Remove a Show Time",
				"Exit"
			);
			
			switch (option) {
				case 1:
					ShowTimeView.displayAllShowTimes();
					break;
					
				case 2:
					ShowTimeEditView.addShowTime();
					break;
					
				case 3:
					ShowTimeEditView.updateShowTime();	
					break;
					
				case 4:
					ShowTimeEditView.removeShowTime();
					break;
					
				case 5:
					NavigationController.goBack();
					return;
			}
		}
	}
}
