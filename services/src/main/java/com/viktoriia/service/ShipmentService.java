package com.viktoriia.service;

import java.util.List; 

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.ShipmentStateEntity;

public interface ShipmentService {

	void add(Shipment shipment);
	
	Shipment getShipmentById(int id);

	List<Shipment> getAllShipments();
	
	List<GoodsEntity> getShipmentGoods(int shipmentId);
	
	List<ShipmentStateEntity> getAllShipmentStates();
}
