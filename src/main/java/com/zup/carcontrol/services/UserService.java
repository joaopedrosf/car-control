package com.zup.carcontrol.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.User;
import com.zup.carcontrol.repositories.CarRepository;
import com.zup.carcontrol.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Transactional
	public User insert(User user) {
		User entity = new User();
		entity = repository.save(user);
		return entity;
	}
	
	@Transactional
	public User getUserById(Long id) {
		User user = repository.getOne(id);
		List<Car> carList = user.getCars();
		updateCarRotation(carList);
		
		return user;
	}
	
	@Transactional
	public void updateCarRotation(List<Car> carList) {
		for (Car car: carList) {
			Car entity = carRepository.getOne(car.getId());
			DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
			
			// Se o dia da semana de hoje é o mesmo dia da semana do rodízio
			if(dayOfWeek.getValue() == (entity.getDiaRodizio().ordinal() + 1)) { 
				entity.setIsDiaRodizio(true);
				carRepository.save(entity);
				continue;
			}
			
			entity.setIsDiaRodizio(false);
			carRepository.save(entity);
		}
	}
}
