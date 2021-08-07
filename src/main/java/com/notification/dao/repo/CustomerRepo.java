package com.notification.dao.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.notification.dao.entites.Customer;

@Repository
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
	
	@Query(value = "SELECT ce.id,ce.email,ce.username,ce.phone_number,ce.created_at from group_customer_entity join customer_entity ce on ce.id = group_customer_entity.customer_id where group_id = :group_id", nativeQuery = true)
	List<Customer> getCustomersGroup(@Param("group_id") long group_id, Pageable pageable);
}
