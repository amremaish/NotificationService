package com.notification.dao.models;

import java.util.HashMap;
import java.util.Map;

public class NotificationModel {
	private long group_id;
	private long customer_id;
	private String type;
	private Map<String, String> templates;
	private Map<String, String> params;

	public NotificationModel() {
		templates = new HashMap<>();
		params = new HashMap<>();
		type = "NULL";
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public Map<String, String> getTemplates() {
		return templates;
	}

	public void setTemplates(Map<String, String> templates) {
		this.templates = templates;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
