package com.viktoriia.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;

public interface EmployeeService {

	void add(Employee employee) throws IOException, TimeoutException;

	void delete(int id);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int id);
	
	List<DepartmentEntity> getAllDepartments();

}
