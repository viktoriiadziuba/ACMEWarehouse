package com.viktoriia.rest;

import java.util.List; 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viktoriia.entity.Employee;
import com.viktoriia.service.impl.EmployeeServiceImpl;

@Path("/employees")
public class EmployeeServer {
	
	private EmployeeServiceImpl empService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		List<Employee> employees = empService.getAll();
		return employees;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void postEmployee(Employee employee) {
		empService.add(employee);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putEmployee(Employee employee) {
		empService.add(employee);
		return Response.status(201).entity(employee).build();
	}	

}

