package com.zup.carcontrol.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zup.carcontrol.entities.enums.DiaRodizio;

@Entity
@Table(name = "tb_car")
public class Car implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String ano;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;
	
	private DiaRodizio diaRodizio;
	
	private boolean isDiaRodizio;
	
	private String valor;

	public Car() {
	}
	
	public Car(Long id, String marca, String modelo, String ano) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
