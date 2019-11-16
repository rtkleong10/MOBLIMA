package model;

import java.io.Serializable;

/**
 * This class contains all the information of a cinema staff
 */
public class CinemaStaff implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = -283352975948508251L;
	
	/**
	 * The username of the cinema staff
	 */
	private String username;
	/**
	 * The password of the cinema staff
	 */
	private String password;

	/**
     * Creats a {@code CinemaStaff} object with the given username and password
     * @param username the username of the cinema staff
     * @param password the password of the cinema staff
     */
	public CinemaStaff(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
     * This method returns true if the login using the password is successful
     * @param password the password to test against the password the cinema staff
     * @return true if the password was correct, false if not
     */
	public boolean login(String password) {
		return password.equals(this.password);
	}
	
	/**
     * This method returns the username of the movie goer
     * @return the username of the movie goer
     */
	public String getUsername() {
		return this.username;
	}
	
	/**
     * This method sets the password of the cinema staff.
     * @param password the new password of the cinema staff
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
