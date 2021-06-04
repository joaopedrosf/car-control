package com.zup.carcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;
	
	public Car insert(Car car) {
		Car entity = new Car();
		entity = repository.save(car);
		return entity;
	}
}
