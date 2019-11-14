package controller;

import view.MenuView;
import view.MovieEditView;

public class MovieEditController implements Controller {

	@Override
	public void start() {
		while (true) {
			int option = MenuView.getMenuOption(
				"What would you like to do?",
				"Add a Movie",
				"Update a Movie",
				"Remove a Movie",
				"Exit"
			);
			
			switch (option) {
				case 1:
					MovieEditView.addMovie();
					break;
					
				case 2:
					MovieEditView.updateMovie();	
					break;
					
				case 3:
					MovieEditView.removeMovie();
					break;
					
				case 4:
					NavigationController.goBack();
					return;
			}
		}
	}
}
