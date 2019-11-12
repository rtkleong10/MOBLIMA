package model;

public enum ShowingStatus implements LabelledItem {
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
 