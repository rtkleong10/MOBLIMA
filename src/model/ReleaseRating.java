package model;

public enum ReleaseRating implements LabelledItem {
	G("G"),
	PG("PG"),
	PG13("PG13"),
	NC16("NC16"),
	M18("M18"),
	R21("R21");
	
	private final String label;

	private ReleaseRating(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
