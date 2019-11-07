package model;

import java.io.Serializable;

public class MovieGoer implements Serializable {
	private static final long serialVersionUID = 7689870075709929042L;
	
	private String username;
	private String name;
	private Integer mobileNumber;
	private String emailAddress;
	private String password;
	
	/**
     * Constructs a {@code MovieGoer} object with its username, name, mobile number, email address and password specified.
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
     * Returns true if the login using the password is successful.
     * @param password the password to test against the password the movie goer
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
     * Returns the name of the movie goer.
     * @return the name of the movie goer
     */
	public String getName() {
		return name;
	}
	
	/**
     * Sets the name of the movie goer.
     * @param name the new name of the movie goer
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Returns the mobile number of the movie goer.
     * @return the mobile number of the movie goer
     */
	public Integer getMobileNumber() {
		return mobileNumber;
	}
	
	/**
     * Sets the mobile number of the movie goer.
     * @param mobileNumber the new mobile number of the movie goer
     */
	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
     * Returns the email address of the movie goer.
     * @return the email address of the movie goer
     */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
     * Sets the email address of the movie goer.
     * @param emailAddress the new email address of the movie goer
     */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
     * Sets the password of the movie goer.
     * @param password the new password of the movie goer
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
