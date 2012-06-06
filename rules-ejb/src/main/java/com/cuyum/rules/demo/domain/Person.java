package com.cuyum.rules.demo.domain;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
/**
 * Modelo de Dominio
 * Representa los datos de una persona
 * 
 * @author german
 *
 */
public class Person implements Serializable {

	private String documentNumber;
	private DocumentType documentType;
	private Integer age;
	private String firstName;
	private String lastName;
	
	
	public Person() {
	
	}

	public Person(String documentNumber, DocumentType documentType,
			Integer age, String firstName, String lastName) {
		super();
		this.documentNumber = documentNumber;
		this.documentType = documentType;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentNumber == null) ? 0 : documentNumber.hashCode());
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
		Person other = (Person) obj;
		if (documentNumber == null) {
			if (other.documentNumber != null)
				return false;
		} else if (!documentNumber.equals(other.documentNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		try {
		    return BeanUtils.describe(this).toString();
		  } catch (Exception e) {
		    Logger.getLogger(this.getClass()).error("Error converting object to String", e);
		  }
		  return super.toString();
	}

}
