package com.viktoriia.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.viktoriia.model.Shipment;
import com.viktoriia.model.enums.ShipmentState;
import com.viktoriia.service.ShipmentService;

@Path("shipmentInfo")
public class ShipmentResource {

	ShipmentService service = new ShipmentService();
	
	@POST
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Shipment createShipment(@FormParam("id") String id,
									 @FormParam("description") String description,
									 @FormParam("quantity") int quantity,
									 @FormParam("state") ShipmentState state,
									 @FormParam("dateOfShipment") String dateOfShipment) {
		Shipment shipment = new Shipment();
		shipment.setId(id);
		shipment.setDescription(description);
		shipment.setQuantity(quantity);
		shipment.setState(state.getFieldState());
		shipment.setDateOfShipment(dateOfShipment);
		Shipment shipmentResponse = service.createShipment(shipment);
		return shipmentResponse;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Shipment> getAllShipments() {
		List<Shipment> listShipment = service.getAllShipment();
		return listShipment;
	}
}
