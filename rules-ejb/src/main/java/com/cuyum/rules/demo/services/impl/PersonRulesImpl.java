package com.cuyum.rules.demo.services.impl;

import javax.ejb.Stateless;

import com.cuyum.rules.demo.domain.Person;
import com.cuyum.rules.demo.exceptions.ValidationException;
import com.cuyum.rules.demo.services.PersonRules;

/**
 * Implementacion default de {@link PersonRulesImpl}
 * @author german
 *
 */
@Stateless
public class PersonRulesImpl implements PersonRules {
	
	/* (non-Javadoc)
	 * @see com.cuyum.rules.demo.services.impl.PersonRules#idAdult(com.cuyum.rules.demo.domain.Person)
	 */
	public boolean isAdult(Person p) throws ValidationException{
		if (p.getAge()==null)
			throw new ValidationException("No se indica la edad de la persona");
		if (p.getAge()<0)
			throw new ValidationException("La edad no puede ser negativa");
		
		return (p.getAge() >= 18);
	}

}
