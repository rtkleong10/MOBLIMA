package model;

/**
 * The age groups for a ticket
 */
public enum AgeGroup implements LabelledItem {
	/**
	 * Child
	 */
	CHILD("Child"),
	/**
	 * Adult
	 */
	ADULT("Adult"),
	/**
	 * Senior citizen
	 */
	SENIOR_CITIZEN("Senior Citizen");
	
	/**
	 * The label of the age group
	 */
	private String label;
	
	/**
	 * Creates an {@code AgeGroup} object with the given label
	 * @param label
	 */
	private AgeGroup(String label) {
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
