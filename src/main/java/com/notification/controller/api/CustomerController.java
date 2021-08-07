package com.notification.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dao.entites.Customer;
import com.notification.error.CustomResponse;
import com.notification.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping("/create")
	public Object createCustomer(@RequestBody Customer cutomerParams) {
		return new CustomResponse(customerService.createCustomer(cutomerParams));
	}

	@PostMapping("/update")
	public Object updateCustomer(@RequestBody Customer cutomerParams) {
		return new CustomResponse(customerService.createCustomer(cutomerParams));
	}

	@GetMapping("/all")
	public Object getCustomers(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize) {
		return new CustomResponse(customerService.findAll(pageNo, pageSize));
	}
}
