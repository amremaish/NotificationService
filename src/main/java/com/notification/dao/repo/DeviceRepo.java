package com.notification.dao.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notification.dao.entites.Device;

@Repository
public interface DeviceRepo extends PagingAndSortingRepository<Device, Long> {
	
	@Query(value = "SELECT * from device_entity where customer_id = :customer_id", nativeQuery = true)
	List<Device> getCustomerDevices(@Param("customer_id") long customer_id, Pageable pageable);
}
