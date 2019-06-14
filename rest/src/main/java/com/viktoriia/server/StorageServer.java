package com.viktoriia.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viktoriia.entity.Storage;
import com.viktoriia.services.StorageService;

@Path("/storages")
public class StorageServer {

	private StorageService str = new StorageService();
	
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
}
