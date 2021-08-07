package com.notification.dao.repo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Group;
import com.notification.dao.entites.UserEntity;

@Repository
public interface GroupRepo extends PagingAndSortingRepository<Group, Long> {
	Optional<Group> findByName(String name);
}
