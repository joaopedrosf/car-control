package com.zup.carcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.carcontrol.dto.UserDto;
import com.zup.carcontrol.dto.UserInsertDto;
import com.zup.carcontrol.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserDto> register(@RequestBody UserInsertDto insertUser) {
		UserDto user = service.insert(insertUser);
		return ResponseEntity.status(201).body(user);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> getUserCars(@PathVariable Long id) {
		UserDto user = service.getUserById(id);
		return ResponseEntity.ok().body(user);
	}
}
