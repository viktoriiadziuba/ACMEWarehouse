package com.viktoriia.service;

import java.util.List;

import com.viktoriia.dao.ShipmentDAO;
import com.viktoriia.model.Shipment;

public class ShipmentService {

	ShipmentDAO shipmentDao = new ShipmentDAO();
	
	public List<Shipment> getAllShipment() {
	List<Shipment> listShipment = shipmentDao.getAllShipments();
	return listShipment;
	}
	
	public Shipment getShipmenttById(String id) {
		Shipment shipmentResponse = shipmentDao.getShipmentById(id);
		return shipmentResponse;
	}
	
	public Shipment createShipment(Shipment shipment) {
		Shipment shipmentResponse = shipmentDao.createShipment(shipment);
		return shipmentResponse;
	}
}
