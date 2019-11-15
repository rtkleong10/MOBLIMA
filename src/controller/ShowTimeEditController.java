package controller;

import view.MenuView;
import view.ShowTimeEditView;

public class ShowTimeEditController implements Controller {

	@Override
	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"Add a Show Time",
				"Update a Show Time",
				"Remove a Show Time",
				"Exit"
			);
			
			switch (option) {
				case 1:
					ShowTimeEditView.addShowTime();
					break;
					
				case 2:
					ShowTimeEditView.updateShowTime();	
					break;
					
				case 3:
					ShowTimeEditView.removeShowTime();
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}
}
