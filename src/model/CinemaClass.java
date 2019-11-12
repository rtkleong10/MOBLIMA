package model;

public enum CinemaClass implements LabelledItem {
	NORMAL("Normal"),
	PLATINUM_MOVIE_SUITE("Platinum Movie Suite");
	
	String label;
	
	private CinemaClass(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}