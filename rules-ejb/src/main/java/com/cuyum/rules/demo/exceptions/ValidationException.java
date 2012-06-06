package com.cuyum.rules.demo.exceptions;

@SuppressWarnings("serial")
/**
 * Excepci�n de negocio
 * Lanzada cuando los datos de entrada no cumple con la precondici�n
 * de ejecuci�n del servicio
 * 
 * @author german
 *
 */
public class ValidationException extends Exception {

	
	public ValidationException(String message) {
		super(message);
	
	}


}
