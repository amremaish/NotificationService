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
import com.notification.util.DateFormat;

@Entity
@Table(name = "group_entity")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@NotNull(message = "name")
	@Column(name = "name")
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormat.DATE_TIME)
	@Column(name = "created_at")
	private Date created_at;

	public Group() {
		this.created_at = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
