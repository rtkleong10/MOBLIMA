package model;

/**
 * The movie types for a movie
 */
public enum MovieType implements LabelledItem {
	/**
	 * Regular movie
	 */
	REGULAR("Regular"),
	/**
	 * Blockbuster movie
	 */
	BLOCKBUSTER("Blockbuster"),
	/**
	 * 3D movie
	 */
	_3D("3D"); 
	
	/**
	 * The label of the movie type
	 */
	private final String label;

	/**
	 * Creates a {@code MovieType} object with the given label
	 * @param label
	 */
	private MovieType(String label) {
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
