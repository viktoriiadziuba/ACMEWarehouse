package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.Department;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4410557928254697842L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private Department department;
	
	
	public DepartmentEntity() {
		
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return String.format("	[Department: "
				+ "id=%d "
				+ "department=%s]", 
				getId(), getDepartment());                               
	}
	
	
}
