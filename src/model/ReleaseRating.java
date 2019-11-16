package model;

/**
 * The release ratings for a movie
 */
public enum ReleaseRating implements LabelledItem {
	/**
	 * G rating
	 */
	G("G"),
	/**
	 * PG rating
	 */
	PG("PG"),
	/**
	 * PG13 rating
	 */
	PG13("PG13"),
	/**
	 * NC16 rating
	 */
	NC16("NC16"),
	/**
	 * M18 rating
	 */
	M18("M18"),
	/**
	 * R21 rating
	 */
	R21("R21");
	
	/**
	 * The label of the release rating
	 */
	private final String label;

	/**
	 * Creates a {@code ReleaseRating} object with the given label
	 * @param label
	 */
	private ReleaseRating(String label) {
		this.label = label;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return label;
	}
}
