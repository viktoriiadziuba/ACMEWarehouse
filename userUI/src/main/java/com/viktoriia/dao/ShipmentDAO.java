package com.viktoriia.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.viktoriia.model.Shipment;
//Map instead of DB 
public class ShipmentDAO {

	static HashMap<String, Shipment> shipmentMap = new HashMap<String, Shipment>();
	
	public List<Shipment> getAllShipments() {
		List<Shipment> shipmentList = new ArrayList<Shipment>(shipmentMap.values());
		return shipmentList;
	}
	
	public Shipment createShipment(Shipment shipment) {
		shipmentMap.put(shipment.getId(), shipment);
		return shipmentMap.get(shipment.getId());		
	}
	
	public Shipment getShipmentById(String id) {
		return shipmentMap.get(id);
	}
}
