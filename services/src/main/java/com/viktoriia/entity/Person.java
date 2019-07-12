package com.viktoriia.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1463896504190006311L;

	@Column(name="full_name", nullable = false)
	private String fullName;
	
	@Column(name="phone_number", nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(name="date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	
	public Person() {
		
	}
	

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return String.format("	[Person: "
				+ "id=%d "
				+ "fullName=%s "
				+ "phoneNumber=%s "
				+ "dateOfBirth=%tD "
				+ "email=%s]", 
				getId(), fullName, phoneNumber, dateOfBirth, email);  
	}
		
}
