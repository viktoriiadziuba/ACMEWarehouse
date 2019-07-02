package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 2609784133470143730L;
	
	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@Column(name="user_name", nullable = false, unique = true)
	private String userName;
	
	@Column(name="password", nullable = false, unique = true)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRoleEntity role;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	

	public User() {
		
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserRoleEntity getRole() {
		return role;
	}


	public void setRole(UserRoleEntity role) {
		this.role = role;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
