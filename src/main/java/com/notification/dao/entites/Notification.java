package com.notification.dao.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.notification.util.DateFormat;

@Entity
@Table(name = "notification_entity", indexes = {
		@Index(name = "group_notify_index", columnList = "group_id", unique = false),
		@Index(name = "device_notify_index", columnList = "device_id", unique = false),
		@Index(name = "customer_notify_index", columnList = "customer_id", unique = false)})
public class Notification {

	public static String NOTIFY_TYPE = "notify", SMS_TYPE = "sms";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "type")
	private String type;

	@Column(name = "language")
	private String language;

	@Column(name = "customer_id")
	private long customer_id;

	@Column(name = "group_id")
	private long group_id;

	@Column(name = "device_id")
	private long device_id;
	
	@Column(name = "is_served")
	private boolean isServed;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormat.DATE_TIME)
	@Column(name = "created_at")
	private Date created_at;

	public Notification() {
		this.created_at = new Date();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public long getDevice_id() {
		return device_id;
	}

	public boolean isServed() {
		return isServed;
	}

	public void setServed(boolean isServed) {
		this.isServed = isServed;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setDevice_id(long device_id) {
		this.device_id = device_id;
	}

}
