package com.notification.dao.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notification.util.DateFormat;

@Entity
@Table(name = "user_entity")
public class UserEntity {

	public static final String USER_ROLE = "USER_ROLE", ADMIN_ROLE = "ADMIN_ROLE";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@NotNull(message = "email must not be empty")
	@Column(name = "email")
	private String email;

	@Column(name = "is_admin")
	private boolean is_admin;
	
	@NotNull(message = "password must not be empty")
	@JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;
	
	@Column(name = "roles")
	private String roles;
	
	@NotNull(message = "username must not be empty")
	@Column(name = "username")
	private String username;


	@Column(name = "active")
	private boolean active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormat.DATE_TIME)
	@Column(name = "created_at")
	private Date created_at;

	public UserEntity() {
		this.roles = ADMIN_ROLE;
		is_admin = false;
		this.created_at = new Date();
		active = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean is_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

}
