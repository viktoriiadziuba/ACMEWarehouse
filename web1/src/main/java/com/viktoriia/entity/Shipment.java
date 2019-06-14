package com.viktoriia.entity;

import java.util.Date;

public class Shipment {

	private String id;

	private Date dateOfShipment;
	private String description;

	public Shipment(String id, Date dateOfShipment, String description) {
		super();
		this.id = id;
		this.dateOfShipment = dateOfShipment;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public Date getDateOfShipment() {
		return dateOfShipment;
	}

	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
