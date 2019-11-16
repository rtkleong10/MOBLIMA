package model;

import java.io.Serializable;

/**
 * This class contains all the information of a movie goer
 */
public class MovieGoer implements Serializable {
	/**
	 * The serialisation version number
	 */
	private static final long serialVersionUID = 7689870075709929042L;
	
	/**
	 * The username of the movie goer
	 */
	private String username;
	/**
	 * The name of the movie goer
	 */
	private String name;
	/**
	 * The mobile number of the movie goer
	 */
	private Integer mobileNumber;
	/**
	 * The email address of the movie goer
	 */
	private String emailAddress;
	/**
	 * The password of the movie goer
	 */
	private String password;
	
	/**
     * Creates a {@code MovieGoer} object with the given username, name, mobile number, email address and password
     * @param username the username of the movie goer
     * @param name the name of the movie goer
     * @param mobileNumber the mobile number of the movie goer
     * @param emailAddress the mobile number of the movie goer
     * @param password the password of the movie goer
     */
	public MovieGoer(String username, String name, Integer mobileNumber, String emailAddress, String password) {
		this.username = username;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	/**
     * This method returns true if the login using the password is successful
     * @param password the password to test against the password the movie goer
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
     * This method returns the name of the movie goer
     * @return the name of the movie goer
     */
	public String getName() {
		return name;
	}
	
	/**
     * This method sets the name of the movie goer.
     * @param name the new name of the movie goer
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * This method returns the mobile number of the movie goer
     * @return the mobile number of the movie goer
     */
	public Integer getMobileNumber() {
		return mobileNumber;
	}
	
	/**
     * This method sets the mobile number of the movie goer.
     * @param mobileNumber the new mobile number of the movie goer
     */
	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
     * This method returns the email address of the movie goer.
     * @return the email address of the movie goer
     */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
     * This method ets the email address of the movie goer.
     * @param emailAddress the new email address of the movie goer
     */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
     * This method sets the password of the movie goer.
     * @param password the new password of the movie goer
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
