package com.notification.dao.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.dao.entites.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}
