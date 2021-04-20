package com.SDEadda247.app.client.create.request.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class createUserRequest {
	@NotNull(message = "First name REQUIRED")
	private String first_name;
	@NotNull(message = "Last name REQUIRED")
	private String last_name;
	@Email
	private String email;
	@Size(min=6)
	private String password;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

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

}
