package com.viktoriia.server;

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
import com.viktoriia.model.impl.OrderServiceImpl;

@Path("/orders")
public class OrderServer {

	private OrderServiceImpl ord = new OrderServiceImpl();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		List<Order> order = ord.getAll();
		return Response.status(200).entity(order).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postOrder(Order order) {
		ord.add(order);
		return Response.status(201).entity(order).build();
	}

	@PUT
	@Path("/put/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putOrder(Order order) {
		ord.add(order);
		return Response.status(201).entity(order).build();
	}
	
}
