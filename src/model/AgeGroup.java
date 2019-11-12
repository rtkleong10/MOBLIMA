package model;

public enum AgeGroup implements LabelledItem {
	CHILD("Child"),
	ADULT("Adult"),
	SENIOR_CITIZEN("Senior Citizen");
	
	String label;
	
	private AgeGroup(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
