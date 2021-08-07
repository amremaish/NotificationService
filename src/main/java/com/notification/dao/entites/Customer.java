package com.notification.dao.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notification.util.DateFormat;

@Entity
@Table(name = "customer_entity")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@NotNull(message = "email must not be empty")
	@Column(name = "email")
	private String email;

	@NotNull(message = "phone_number must not be empty")
	@Column(name = "phone_number")
	private String phone_number;

	@NotNull(message = "username must not be empty")
	@Column(name = "username")
	private String username;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormat.DATE_TIME)
	@Column(name = "created_at")
	private Date created_at;

	public Customer() {
		this.created_at = new Date();
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
