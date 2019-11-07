package model;

import java.io.Serializable;

public class CinemaStaff implements Serializable {
	private static final long serialVersionUID = -283352975948508251L;
	
	private String username;
	private String password;

	/**
     * Constructs a {@code CinemaStaff} object with its username and password specified.
     * @param username the username of the cinema staff
     * @param password the password of the cinema staff
     */
	public CinemaStaff(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
     * Returns true if the login using the password is successful.
     * @param password the password to test against the password the cinema staff
     * @return true if the password was correct, false if not
     */
	public boolean login(String password) {
		return password.equals(this.password);
	}
	
	/**
     * Returns the username of the movie goer.
     * @return the username of the movie goer
     */
	public String getUsername() {
		return this.username;
	}
	
	/**
     * Sets the password of the cinema staff.
     * @param password the new password of the cinema staff
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
