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

import com.viktoriia.entity.Shipment;
import com.viktoriia.service.impl.ShipmentServiceImpl;

@Path("/shipments")
public class ShipmentServer {

	private ShipmentServiceImpl shpService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShipments() {
		List<Shipment> shipment = shpService.getAll();
		return Response.status(200).entity(shipment).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postShipment(Shipment shipment) {
		shpService.add(shipment);
		return Response.status(201).entity(shipment).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putShipment(Shipment shipment) {
		shpService.add(shipment);
		return Response.status(201).entity(shipment).build();
	}
}

