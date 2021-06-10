package com.zup.carcontrol.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.carcontrol.dto.UserDto;
import com.zup.carcontrol.dto.UserInsertDto;
import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.User;
import com.zup.carcontrol.repositories.CarRepository;
import com.zup.carcontrol.repositories.UserRepository;
import com.zup.carcontrol.services.exceptions.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Transactional
	public UserDto insert(UserInsertDto dto) {
		User entity = new User(dto);
		entity = repository.save(entity);
		
		UserDto responseDto = new UserDto(entity);
		return responseDto;
	}
	
	@Transactional
	public UserDto getUserById(Long id) {
		Optional<User> userObj = repository.findById(id);
		User user = userObj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		
		List<Car> carList = user.getCars();
		updateCarRotation(carList);
		
		UserDto dto = new UserDto(user);
		return dto;
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
