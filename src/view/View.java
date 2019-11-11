package view;

public abstract class View {
	public View previousView;
	
	public abstract void start();
	
	protected void exit() {
		if (previousView != null) {
			System.out.println();
			previousView.start();
		}
	}
	
	protected void load(View nextView) {
		System.out.println();
		nextView.previousView = this;
		nextView.start();
	}
}
