package com.viktoriia.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viktoriia.services.EmployeeService;
import com.viktoriia.entity.Employee;

@Path("/employees")
public class EmployeeServer {
	
	private EmployeeService emp = new EmployeeService();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		List<Employee> employees = emp.getAll();
		return Response.status(200).entity(employees).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEmployee(Employee employee) {
		emp.add(employee);
		return Response.status(201).entity(employee).build();
	}


}
