package com.viktoriia.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.viktoriia.model.Equipment;
import com.viktoriia.model.enums.EquipmentType;
import com.viktoriia.service.EquipmentService;

@Path("equipmentInfo")
public class EquipmentResource {

	EquipmentService service = new EquipmentService();
	
	@POST
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Equipment createEquipment(@FormParam("id") String id,
									 @FormParam("description") String description,
									 @FormParam("quantity") int quantity,
									 @FormParam("type") EquipmentType type) {
		Equipment equipment = new Equipment();
		equipment.setId(id);
		equipment.setDescription(description);
		equipment.setQuantity(quantity);
		equipment.setType(type);
		Equipment equipmentResponse = service.createEquipment(equipment);
		return equipmentResponse;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Equipment> getAllEquipment() {
		List<Equipment> listEquipment = service.getAllEquipment();
		return listEquipment;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Equipment getEquipmentById(@PathParam("id") String id) {
		return service.getEquipmentById(id);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Equipment updateEquipment(@FormParam("id") String id,
									 @FormParam("description") String description,
									 @FormParam("quantity") int quantity,
									 @FormParam("type") EquipmentType type) {
		Equipment eqv = new Equipment(id, quantity, description, type);
		service.updateEquipment(eqv);
		return eqv;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteUser(@PathParam("id") String id) {
		service.deleteEquipment(id);
	}
}

