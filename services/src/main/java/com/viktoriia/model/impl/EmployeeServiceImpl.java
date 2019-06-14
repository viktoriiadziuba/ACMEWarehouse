package com.viktoriia.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.viktoriia.entity.Employee;
import com.viktoriia.model.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	public static List<Employee> employees = new ArrayList<Employee>();

	public void add(Employee employee) {
		employees.add(employee);
	}

	public void update(Employee employee) {
		employees.add(employee);

	}

	public void delete(Employee employee) {
		employees.remove(employee);

	}

	public List<Employee> getAll() {
		return employees;
	}

}
