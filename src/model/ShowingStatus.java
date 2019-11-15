package model;

public enum ShowingStatus implements LabelledItem {
	COMING_SOON("Coming Soon"),
	PREVIEW("Preview"),
	NOW_SHOWING("Now Showing"),
	END_OF_SHOWING("End of Showing");
	
	String label;
	/**
	 * Creates ShowingStatus object instance with the label as specified in the parameter
	 * @param label
	 */
	private ShowingStatus(String label) {
		this.label = label;
	}
	/**
	 * Returns the label of the ShowingStatus object
	 */
	public String getLabel() {
		return label;
	}
}
 