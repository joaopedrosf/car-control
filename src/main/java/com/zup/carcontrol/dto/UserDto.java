package com.zup.carcontrol.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.User;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String email;

	private String cpf;
	
	private String dataNascimento;
	
	private List<CarDto> cars = new ArrayList<>();

	public UserDto() {
	}

	public UserDto(Long id, String nome, String email, String cpf, String dataNascimento, List<CarDto> cars) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.cars = cars;
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.dataNascimento = user.getDataNascimento();
		this.cars = carEntityToDto(user.getCars());
	}
	
	private List<CarDto> carEntityToDto (List<Car> carList) {
		List<CarDto> carDtoList = new ArrayList<>(); 
		
		for(Car car: carList) {
			carDtoList.add(new CarDto(car));
		}
		
		return carDtoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<CarDto> getCars() {
		return cars;
	}

	public void setCars(List<CarDto> cars) {
		this.cars = cars;
	}
	
}
