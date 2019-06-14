package com.viktoriia.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.enums.Department;

@XmlRootElement
@Entity
@Table(name = "employee")
public class Employee extends Person {

	@Enumerated(EnumType.STRING)
	private Department department;

	public Employee() {
			
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
