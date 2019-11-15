package model;

public enum DateType implements LabelledItem {
	WEEKDAY("Weekday"),
	WEEKEND("Weekend"),
	HOLIDAY("Holiday");
	
	private String label;
	
	private DateType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
