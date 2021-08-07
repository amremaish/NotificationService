package com.notification.dao.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.notification.dao.entites.GroupCustomer;

@Repository
public interface GroupCustomerRepo extends PagingAndSortingRepository<GroupCustomer, Long> {
	

}
