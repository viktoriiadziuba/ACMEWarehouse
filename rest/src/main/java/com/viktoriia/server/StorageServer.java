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

import com.viktoriia.entity.Storage;
import com.viktoriia.model.impl.StorageServiceImpl;

@Path("/storages")
public class StorageServer {

	private StorageServiceImpl str = new StorageServiceImpl();
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStorages() {
		List<Storage> storage = str.getAll();
		return Response.status(200).entity(storage).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postStorage(Storage storage) {
		str.add(storage);
		return Response.status(201).entity(storage).build();
	}
	
	@PUT
	@Path("/put/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putStorage(Storage storage) {
		str.add(storage);
		return Response.status(201).entity(storage).build();
	}
}
