package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 7702555619063933201L;
	
	@OneToOne
	@JoinColumn(name = "persons_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "departments_id")
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
		return String.format("[Employee: "
				+ "id=%d "
				+ "\n" + "%s "
				+ "\n" + "%s]", 
				getId(), person, department);                          
	}	
	
}
