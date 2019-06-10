package com.viktoriia.model.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import com.viktoriia.entity.Employee;
import com.viktoriia.model.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private List<Employee> employees = new ArrayList<Employee>();

	public void add(Employee employee) {
		employees.add(employee);
	}

	public void update(Employee employee, String[] params) {
		employee.setDepartment(Objects.requireNonNull(params[0]));
		employee.setEmail(Objects.requireNonNull(params[1]));
		employee.setPhoneNumber(Objects.requireNonNull(params[2]));

		employees.add(employee);

	}

	public void delete(Employee employee) {
		employees.remove(employee);

	}

	public List<Employee> getAll() {
		return employees;
	}

}
