package com.notification.error;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.notification.util.DateFormat;



public class CustomResponse {

	private String message;
	private int status_code;
	private String time;
	private Object details;

	public CustomResponse(Object details) {
		this.message = "Successfully Done";
		this.details = details;
		this.status_code = HttpStatus.OK.value();
        Date date = Calendar.getInstance().getTime();  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        this.time = dateFormat.format(date);  
	}

	public CustomResponse(Object details, int status_code) {
		this.message = "Successfully Done";
		this.details = details;
		this.status_code = status_code;
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.DATE_TIME);
		sdf.format(new Date());
		this.time = sdf.format(DateFormat.DATE_TIME);
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}