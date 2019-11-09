package model;

public enum MovieType {
	REGULAR("Regular"),
	BLOCKBUSTER("Blockbuster"),
	_3D("3D"); 
	
	public final String label;

	private MovieType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
