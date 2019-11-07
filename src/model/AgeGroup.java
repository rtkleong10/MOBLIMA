package model;

public enum AgeGroup {
	CHILD("Child"),
	ADULT("Adult"),
	SENIORs_CITIZEN("Senior Citizen"); 
	
	public final String ageGroup;

	private AgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
}
