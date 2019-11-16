package controller;

import view.ShowTimeView;

/**
 * This class controls the display of all the available show times
 */
public class ShowTimeController implements Controller {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		ShowTimeView.displayAllShowTimes();
		NavigationController.goBack();
	}
}
