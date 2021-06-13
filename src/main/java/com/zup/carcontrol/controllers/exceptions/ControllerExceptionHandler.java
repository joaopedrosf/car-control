package com.zup.carcontrol.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.carcontrol.services.exceptions.CodigoNotFoundException;
import com.zup.carcontrol.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest req) {
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Entidade não encontrada");
		err.setMessage(e.getMessage());
		err.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> propertyValue(MethodArgumentNotValidException e, HttpServletRequest req) {
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Preenchimento incorreto de dados");
		err.setMessage("Tipo de dado incorreto ou valores nulos em campos obrigatórios");
		err.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(CodigoNotFoundException.class)
	public ResponseEntity<StandardError> codigoNotFound(CodigoNotFoundException e, HttpServletRequest req) {
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Valor não encontrado na tabela FIPE");
		err.setMessage(e.getMessage());
		err.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataErrorOnBodyRequest(DataIntegrityViolationException e, HttpServletRequest req){
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Erro nos dados do corpo da requisição");
		err.setMessage("O preenchimento do corpo da requisição foi feito com dados inválidos ou inexistentes");
		err.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); 
	}
}
