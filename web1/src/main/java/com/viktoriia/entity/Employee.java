package com.viktoriia.entity;

import java.util.Date;

public class Employee {

	private String id;

	private String name;
	private String department;
	private String surname;
	private String phoneNumber;
	private Date dateOfBirth;
	private String email;

	public Employee(String id, String name, String department, String surname, String phoneNumber, Date dateOfBirth,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
