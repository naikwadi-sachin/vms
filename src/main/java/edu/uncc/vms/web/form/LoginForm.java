package edu.uncc.vms.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {

	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	private String userType;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password
				+ ", userType=" + userType + "]";
	}


	
	
}
