package com.notification.dao.repo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.notification.dao.entites.Customer;
import com.notification.dao.entites.UserEntity;

@Repository
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
}
