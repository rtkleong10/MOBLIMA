package controller;

import view.ShowTimeView;

public class ShowTimeController implements Controller {

	@Override
	public void start() {
		ShowTimeView.displayAllShowTimes();
		NavigationController.goBack();
	}
}
