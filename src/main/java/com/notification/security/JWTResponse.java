package com.notification.security;

public class JWTResponse {

	private String token;
	private String email;
	private String username;
	private String roles;
	private String status;

	public JWTResponse(String token, String email, String username, String roles, String status) {
		this.status = status;
		this.token = token;
		this.email = email;
		this.roles = roles;
		this.username = username;
	}

	public JWTResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
