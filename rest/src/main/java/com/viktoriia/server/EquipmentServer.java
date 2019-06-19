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

import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.model.impl.EquipmentServiceImpl;

@Path("/equipment")
public class EquipmentServer {

	private EquipmentServiceImpl eqv = new EquipmentServiceImpl();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEquipment() {
		List<EquipmentEntity> equipment = eqv.getAll();
		return Response.status(200).entity(equipment).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEmployee(EquipmentEntity equipment) {
		eqv.add(equipment);
		return Response.status(201).entity(equipment).build();
	}

	@PUT
	@Path("/put/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putEmployee(EquipmentEntity equipment) {
		eqv.add(equipment);
		return Response.status(201).entity(equipment).build();
	}

}
