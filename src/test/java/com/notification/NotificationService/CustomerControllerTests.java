package com.notification.NotificationService;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.notification.dao.entites.Customer;
import com.notification.service.CustomerService;

@SpringBootTest
class CustomerControllerTests {
	@Autowired
	private CustomerService customerService;

	@Test
	Customer testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("test@test.com");
		customer.setLanguage("en");
		customer.setPhone_number("123456");
		customer.setUsername("test");
		Customer savedCustomer = customerService.createCustomer(customer);
		assertThat(customer.getEmail()).isEqualTo(savedCustomer.getEmail());
		return savedCustomer;
	}
	@Test
	void testFindCustomer() {
		Customer customer = this.testCreateCustomer();
		Customer foundCustomer =  customerService.findbyId(customer.getId());
		assertThat(foundCustomer.getId()).isEqualTo(foundCustomer.getId());
	}

}
