package com.viktoriia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Shipment")
public class Shipment {

	private String id;
	private String dateOfShipment;
	private String description;
	private String state;
	private int quantity;
	
	
	public Shipment() {
		
	}

	public Shipment(String id, String dateOfShipment, String description, String state, int quantity) {
		super();
		this.id = id;
		this.dateOfShipment = dateOfShipment;
		this.description = description;
		this.state = state;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	public String getDateOfShipment() {
		return dateOfShipment;
	}
	
	@XmlElement
	public void setDateOfShipment(String dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	
	@XmlElement
	public void setState(String state) {
		this.state = state;
	}
	public int getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
