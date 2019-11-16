package model;

/**
 * The date types
 */
public enum DateType implements LabelledItem {
	/**
	 * Weekday
	 */
	WEEKDAY("Weekday"),
	/**
	 * Weekend
	 */
	WEEKEND("Weekend"),
	/**
	 * Holiday
	 */
	HOLIDAY("Holiday");
	
	/**
	 * The label of the date type
	 */
	private String label;
	
	/**
	 * Creates a {@code DateType} object with the given label
	 * @param label
	 */
	private DateType(String label) {
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
