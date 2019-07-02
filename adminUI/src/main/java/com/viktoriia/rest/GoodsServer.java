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

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.service.impl.GoodsServiceImpl;

@Path("/goods")
public class GoodsServer {
	
	private GoodsServiceImpl gdsService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGoods() {
		List<GoodsEntity> goods = gdsService.getAll();
		return Response.status(200).entity(goods).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postGoods(GoodsEntity goods) {
		gdsService.add(goods);
		return Response.status(201).entity(goods).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putGoods(GoodsEntity goods) {
		gdsService.add(goods);
		return Response.status(201).entity(goods).build();
	}
}
