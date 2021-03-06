package com.zup.carcontrol.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.carcontrol.entities.User;

public class CarInsertDto implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String marca;
	
	@NotBlank
	private String modelo;
	
	@NotBlank
	private String ano;
	
	@NotNull
	private User user;

	public CarInsertDto() {
	}

	public CarInsertDto(String marca, String modelo, String ano, User user) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.user = user;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
