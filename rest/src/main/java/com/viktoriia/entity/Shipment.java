package com.viktoriia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Shipment {

	private String id;

	private String dateOfShipment;
	private String description;

	public Shipment() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateOfShipment() {
		return dateOfShipment;
	}

	public void setDateOfShipment(String dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
