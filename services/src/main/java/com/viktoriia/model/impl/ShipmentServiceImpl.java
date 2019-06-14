package com.viktoriia.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viktoriia.entity.Shipment;
import com.viktoriia.model.ShipmentService;

public class ShipmentServiceImpl implements ShipmentService {

	private List<Shipment> shipments = new ArrayList<Shipment>();

	public void add(Shipment shipment) {
		shipments.add(shipment);
	}

	public void update(Shipment shipment, Date newDate, String newDescription) {
		shipment.setDateOfShipment(newDate);
		shipment.setDescription(newDescription);

		shipments.add(shipment);

	}

	public void delete(Shipment shipment) {
		shipments.remove(shipment);

	}

	public List<Shipment> getAll() {
		return shipments;
	}

}
