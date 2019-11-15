/**
 *  Cinema classes that can be used
 */
package model;
public enum CinemaClass implements LabelledItem {
	/**
     * Normal cinema class
     */
	NORMAL("Normal"),
	/**
     * Platinum Movie Suite cinema class
     */
	PLATINUM_MOVIE_SUITE("Platinum Movie Suite");
	
	private String label;
	/**
     * Constructor for each enum
     * @param label the name of the cinema class
     */
	private CinemaClass(String label) {
		this.label = label;
	}
	/** 
     * Returns the label parameter of the enum class
     * @return the label of the enum
     */
	public String getLabel() {
		return label;
	}
}