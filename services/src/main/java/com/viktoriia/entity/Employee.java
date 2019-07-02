package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 7702555619063933201L;
	
	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;

	
	public Employee() {
		
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public DepartmentEntity getDepartment() {
		return department;
	}


	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "Employee [person=" + person + ", department=" + department + 
				", getId()=" + getId() + "]";
	}	

}
