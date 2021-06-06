package com.zup.carcontrol.dto;

import java.io.Serializable;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.enums.DiaRodizio;

public class CarDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String marca;
	
	private String modelo;
	
	private String ano;
	
	private DiaRodizio diaRodizio;
	
	private boolean isDiaRodizio;
	
	private String valor;

	public CarDto() {
	}

	public CarDto(Long id, String marca, String modelo, String ano, DiaRodizio diaRodizio,
			boolean isDiaRodizio, String valor) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.diaRodizio = diaRodizio;
		this.isDiaRodizio = isDiaRodizio;
		this.valor = valor;
	}
	
	public CarDto(Car car) {
		this.id = car.getId();
		this.marca = car.getMarca();
		this.modelo = car.getModelo();
		this.ano = car.getAno();
		this.diaRodizio = car.getDiaRodizio();
		this.isDiaRodizio = car.getIsDiaRodizio();
		this.valor = car.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public DiaRodizio getDiaRodizio() {
		return diaRodizio;
	}

	public void setDiaRodizio(DiaRodizio diaRodizio) {
		this.diaRodizio = diaRodizio;
	}

	public boolean getIsDiaRodizio() {
		return isDiaRodizio;
	}

	public void setIsDiaRodizio(boolean isDiaRodizio) {
		this.isDiaRodizio = isDiaRodizio;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
