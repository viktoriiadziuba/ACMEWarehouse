package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.Employee;

@XmlRootElement
public class EmployeeService {
	
	public static List<Employee> employees = new ArrayList<>();

	public void add(Employee employee) {
		employees.add(employee);
	}

	@XmlElement(name = "listEmployees")
	public List<Employee> getAll() {
		return employees;
	}

}
