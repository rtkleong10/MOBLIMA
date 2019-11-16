package model;

/**
 * The cinema classes for a cinema
 */
public enum CinemaClass implements LabelledItem {
	/**
     * Normal cinema class
     */
	NORMAL("Normal"),
	/**
     * Platinum Movie Suite cinema class
     */
	PLATINUM_MOVIE_SUITE("Platinum Movie Suite");
	
	/**
	 * The label of the cineme class
	 */
	private String label;
	
	/**
	 * Creates a {@code CinemaClass} object with the given label
	 * @param label
	 */
	private CinemaClass(String label) {
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