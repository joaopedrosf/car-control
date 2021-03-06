package com.zup.carcontrol.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserInsertDto implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String cpf;
	
	@NotBlank
	private String dataNascimento;

	public UserInsertDto() {
	}

	public UserInsertDto(String nome, String email, String cpf, String dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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
	
}
