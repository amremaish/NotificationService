package com.notification.error;

public class SuccessResponsePage extends CustomResponse {

	private int current_page;
	private int total_pages;
	private int number_of_items;

	public SuccessResponsePage(Object message, int current_page, int total_pages) {
		super(message);
		this.current_page = current_page;
		this.total_pages = total_pages;
	}

	public SuccessResponsePage(Object message, int current_page, int total_pages, int number_of_items) {
		super(message);
		this.current_page = current_page;
		this.total_pages = total_pages;
		this.number_of_items = number_of_items;
	}

	public int getNumber_of_items() {
		return number_of_items;
	}

	public void setNumber_of_items(int number_of_items) {
		this.number_of_items = number_of_items;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

}
