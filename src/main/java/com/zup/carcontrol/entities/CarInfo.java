package com.zup.carcontrol.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CarInfo {

	private String valor;
	private String marca;
	private String modelo;
	private Integer anoModelo;
	private String combustivel;
	private String codigoFipe;
	private String mesReferencia;
	private Integer tipoVeiculo;
	private String siglaCombustivel;
	
	public CarInfo() {
	}

	public CarInfo(String valor, String marca, String modelo, Integer anoModelo, String combustivel, String codigoFipe,
			String mesReferencia, Integer tipoVeiculo, String siglaCombustivel) {
		super();
		this.valor = valor;
		this.marca = marca;
		this.modelo = modelo;
		this.anoModelo = anoModelo;
		this.combustivel = combustivel;
		this.codigoFipe = codigoFipe;
		this.mesReferencia = mesReferencia;
		this.tipoVeiculo = tipoVeiculo;
		this.siglaCombustivel = siglaCombustivel;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCodigoFipe() {
		return codigoFipe;
	}

	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}

	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}

	@Override
	public String toString() {
		return "CarInfo [valor=" + valor + ", marca=" + marca + ", modelo=" + modelo + ", anoModelo=" + anoModelo
				+ ", combustivel=" + combustivel + ", codigoFipe=" + codigoFipe + ", mesReferencia=" + mesReferencia
				+ ", tipoVeiculo=" + tipoVeiculo + ", siglaCombustivel=" + siglaCombustivel + "]";
	}

}
