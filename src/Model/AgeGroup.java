package Model;

public enum AgeGroup {
	CHILD("Child"),
	ADULT("Adult"),
	SENIOR_CITIZEN("Senior Citizen"); 
	
	public final String ageGroup;

	private AgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
}
