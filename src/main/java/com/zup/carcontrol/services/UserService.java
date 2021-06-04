package com.zup.carcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.carcontrol.entities.User;
import com.zup.carcontrol.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User insert(User user) {
		User entity = new User();
		entity = repository.save(user);
		return entity;
	}
}
