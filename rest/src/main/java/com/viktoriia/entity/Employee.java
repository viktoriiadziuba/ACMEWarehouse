package com.viktoriia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

	private String id;

	private String name;
	private String department;
	private String surname;
	private String phoneNumber;
	private String dateOfBirth;
	private String email;

	public Employee() {
		
	}

	public String getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSurname() {
		return surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
