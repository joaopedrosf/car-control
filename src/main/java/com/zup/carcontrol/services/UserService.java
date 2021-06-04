package com.zup.carcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.carcontrol.entities.User;
import com.zup.carcontrol.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User insert(User user) {
		User entity = new User();
		entity = repository.save(user);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		User user = repository.getOne(id);
		return user;
	}
}
