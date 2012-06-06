package com.cuyum.rules.demo.exceptions;

@SuppressWarnings("serial")
/**
 * Excepción de negocio
 * Lanzada cuando los datos de entrada no cumple con la precondición
 * de ejecución del servicio
 * 
 * @author german
 *
 */
public class ValidationException extends Exception {

	
	public ValidationException(String message) {
		super(message);
	
	}


}
