package com.viktoriia.entity;

import java.util.Date;

import com.viktoriia.entity.enums.UserRole;

public class User {

	private String id;

	private String userName;
	private String password;
	private String fullName;
	private String phoneNumber;
	private String email;
	private Date dateOfBirth;
	private UserRole role;

	public User(String id, String userName, String password, String fullName, String phoneNumber, String email,
			Date dateOfBirth, UserRole role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
