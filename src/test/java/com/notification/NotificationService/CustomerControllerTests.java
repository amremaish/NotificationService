package com.notification.NotificationService;

import com.notification.dao.entites.Customer;
import com.notification.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
        Customer ex = customerService.findByEmail(customer.getEmail()).orElse(null);
        Customer savedCustomer = null;
        if (ex == null) {
            savedCustomer = customerService.createCustomer(customer);
        } else {
            return null;
        }
        assertThat(customer.getEmail()).isEqualTo(savedCustomer.getEmail());
        return savedCustomer;
    }

    @Test
    void testFindCustomer() {
        Customer customer = this.testCreateCustomer();
        if (customer == null) {
            return;
        }
        Customer foundCustomer = customerService.findbyId(customer.getId());
        assertThat(foundCustomer.getId()).isEqualTo(foundCustomer.getId());
    }

}
