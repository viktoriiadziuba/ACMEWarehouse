package com.viktoriia.model;

import java.util.List;

import com.viktoriia.entity.Employee;

public interface EmployeeService {

	void add(Employee employee);

	void update(Employee employee, String[] params);

	void delete(Employee employee);

	List<Employee> getAll();

}
