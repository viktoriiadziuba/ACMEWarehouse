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

import com.viktoriia.entity.Order;
import com.viktoriia.service.impl.OrderServiceImpl;

@Path("/orders")
public class OrderServer {

	private OrderServiceImpl ordService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		List<Order> order = ordService.getAll();
		return Response.status(200).entity(order).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postOrder(Order order) {
		ordService.add(order);
		return Response.status(201).entity(order).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putOrder(Order order) {
		ordService.add(order);
		return Response.status(201).entity(order).build();
	}
}
