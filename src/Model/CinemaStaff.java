package Model;

import java.io.Serializable;

public class CinemaStaff implements Serializable {
	private static final long serialVersionUID = -283352975948508251L;
	
	private String username;
	private String password;
	
	public CinemaStaff(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean login(String password) {
		return password == this.password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
