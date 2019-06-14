package com.viktoriia.model;

import java.util.Date;
import java.util.List;

import com.viktoriia.entity.Shipment;

public interface ShipmentService {

	void add(Shipment shipment);

	void update(Shipment shipment, Date newDate, String newDescription);

	void delete(Shipment shipment);

	List<Shipment> getAll();
}
