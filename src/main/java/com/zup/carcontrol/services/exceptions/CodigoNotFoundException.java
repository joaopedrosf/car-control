package com.zup.carcontrol.services.exceptions;

public class CodigoNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CodigoNotFoundException(String msg) {
		super(msg);
	}
}
