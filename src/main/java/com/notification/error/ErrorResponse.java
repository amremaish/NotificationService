package com.notification.error;

import java.util.Date;
import java.util.List;

public class ErrorResponse {

	private String message;
	private int status_code;
	private Date time;
	private List<String> details;

	public ErrorResponse(String message, int statusCode, List<String> details) {
		this.message = message;
		this.status_code = statusCode;
		this.details = details;
		this.time = new Date();
	}

	public ErrorResponse() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}