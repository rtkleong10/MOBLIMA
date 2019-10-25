package Model;

import java.io.Serializable;

public class MovieGoer implements Serializable {
	private static final long serialVersionUID = 7689870075709929042L;

	private String name;
	private Integer mobileNumber;
	private String emailAddress;
	private AgeGroup ageGroup;
	private String password;

	public MovieGoer(String name, Integer mobileNumber, String emailAddress, AgeGroup ageGroup, String password) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public boolean login(String password) {
		return password == this.password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
