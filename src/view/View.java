package view;

public abstract class View {
	public View previousView;
	
	public abstract void start();
	
	protected void exit() {
		if (previousView == null) // Exit if it doesn't have previous view
			System.exit(1);
		else
			previousView.start();
	}
	
	protected void load(View nextView) {
		nextView.previousView = this;
		nextView.start();
	}
}