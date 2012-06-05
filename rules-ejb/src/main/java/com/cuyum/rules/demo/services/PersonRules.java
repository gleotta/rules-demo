package com.cuyum.rules.demo.services;

import javax.ejb.Local;

import com.cuyum.rules.demo.domain.Person;
import com.cuyum.rules.demo.exceptions.ValidationException;

@Local
public interface PersonRules {

	/**
	 * Indica si una persona es adulta
	 * 
	 * @param p datos de la persona
	 * @return true si es adulta, o false en casoc ontrario
	 * @throws ValidationException en caso de que edad sea nula o negativa
	 */
	public abstract boolean isAdult(Person p) throws ValidationException;

}