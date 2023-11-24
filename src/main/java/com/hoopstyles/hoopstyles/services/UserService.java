package com.hoopstyles.hoopstyles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	public UserHoop registrar(UserHoop u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repository.save(u);
	}
	
	public UserHoop findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public UserHoop buscarPorEmail(String email) {
		return repository.findFirstByEmail(email);
	}
}
