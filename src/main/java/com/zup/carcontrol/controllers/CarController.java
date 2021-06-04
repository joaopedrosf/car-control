package com.zup.carcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.services.CarService;

@RestController
@RequestMapping(value = "/car")
public class CarController {

	@Autowired
	private CarService service;
	
	@PostMapping
	public ResponseEntity<Car> register(@RequestBody Car insertCar) {
		Car car = new Car();
		car = service.insert(insertCar);
		return ResponseEntity.ok().body(car);
	}
}
