package com.notification.dao.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.notification.dao.entites.Notification;

@Repository
public interface NotificationRepo extends PagingAndSortingRepository<Notification, Long> {
	@Query(value = "SELECT * from notification_entity where group_id = :group_id", nativeQuery = true)
	List<Notification> findByGroupId(@Param("group_id") long group_id, Pageable pageable);

	@Query(value = "SELECT * from notification_entity where group_id = :group_id and customer_id = :customer_id", nativeQuery = true)
	List<Notification> findByCustomerGroupId(@Param("group_id") long group_id,
			@Param("customer_id") long customer_id, Pageable pageable);

	@Query(value = "SELECT * from notification_entity where group_id = :group_id and customer_id = :customer_id and device_id = :device_id", nativeQuery = true)
	List<Notification> findByCustomerGroupDeviceId(@Param("group_id") long group_id,
			@Param("customer_id") long customer_id, @Param("device_id") long device_id, Pageable pageable);
}
