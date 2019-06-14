package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.Shipment;

@XmlRootElement
public class ShipmentService {

	public static List<Shipment> shipments = new ArrayList<>();

	public void add(Shipment shipment) {
		shipments.add(shipment);
	}

	@XmlElement(name = "listShipments")
	public List<Shipment> getAll() {
		return shipments;
	}
}
