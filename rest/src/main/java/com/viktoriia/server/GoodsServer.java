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

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.model.impl.GoodsServiceImpl;

@Path("/goods")
public class GoodsServer {
	
	private GoodsServiceImpl eqv = new GoodsServiceImpl();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGoods() {
		List<GoodsEntity> goods = eqv.getAll();
		return Response.status(200).entity(goods).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postGoods(GoodsEntity goods) {
		eqv.add(goods);
		return Response.status(201).entity(goods).build();
	}
	
	@PUT
	@Path("/put/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putGoods(GoodsEntity goods) {
		eqv.add(goods);
		return Response.status(201).entity(goods).build();
	}

}
