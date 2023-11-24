package com.hoopstyles.hoopstyles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.UserHoop;

public interface UserRepository extends JpaRepository<UserHoop, Long> {

	// Los emails deberían ser unicos
	UserHoop findFirstByEmail(String email);
}
