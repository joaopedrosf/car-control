package com.zup.carcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.carcontrol.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
