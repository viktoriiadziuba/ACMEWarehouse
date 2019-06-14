package com.viktoriia.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viktoriia.entity.Shipment;
import com.viktoriia.services.ShipmentService;

@Path("/shipments")
public class ShipmentServer {

	private ShipmentService shp = new ShipmentService();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShipments() {
		List<Shipment> shipment = shp.getAll();
		return Response.status(200).entity(shipment).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postShipment(Shipment shipment) {
		shp.add(shipment);
		return Response.status(201).entity(shipment).build();
	}
}
