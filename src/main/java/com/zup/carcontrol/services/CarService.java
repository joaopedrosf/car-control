package com.zup.carcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.enums.DiaRodizio;
import com.zup.carcontrol.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;
	
	@Transactional
	public Car insert(Car car) {
		Car entity = new Car();
		entity = fillEntity(entity, car);
		entity = repository.save(entity);
		return entity;
	}
	
	private Car fillEntity(Car entity, Car dto) {
		entity.setAno(dto.getAno());
		entity.setMarca(dto.getMarca());
		entity.setModelo(dto.getModelo());
		entity.setUser(dto.getUser());
		
		DiaRodizio diaRodizio = null;
		Integer anoInteiro = Integer.valueOf(dto.getAno().split("-")[0]);
		int ultimoDigito = anoInteiro % 10;
		
		switch(ultimoDigito) {
		case 0: 
		case 1:
			diaRodizio = DiaRodizio.SEGUNDA_FEIRA;
			break;
		case 2: 
		case 3:
			diaRodizio = DiaRodizio.TERCA_FEIRA;
			break;
		case 4: 
		case 5:
			diaRodizio = DiaRodizio.QUARTA_FEIRA;
			break;
		case 6: 
		case 7:
			diaRodizio = DiaRodizio.QUINTA_FEIRA;
			break;
		case 8: 
		case 9:
			diaRodizio = DiaRodizio.SEXTA_FEIRA;
			break;
		}
		
		entity.setDiaRodizio(diaRodizio);
		
		return entity;
	}
}
