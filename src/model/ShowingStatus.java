package model;

public enum ShowingStatus {
	COMING_SOON("Coming Soon"),
	PREVIEW("Preview"),
	NOW_SHOWING("Now Showing"),
	END_OF_SHOWING("End of Showing");
	
	String label;
	
	private ShowingStatus(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
 