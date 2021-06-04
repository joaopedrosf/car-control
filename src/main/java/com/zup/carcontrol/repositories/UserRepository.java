package com.zup.carcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.carcontrol.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
