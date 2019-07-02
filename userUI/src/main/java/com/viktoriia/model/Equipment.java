package com.viktoriia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.model.enums.EquipmentType;

@XmlRootElement(name="Equipment")
public class Equipment {

	private String id;
	private int quantity;
	private String description;
	private EquipmentType type;
	
	public Equipment() {
		
	}

	public Equipment(String id, int quantity, String description, EquipmentType type) {
		this.id = id;
		this.quantity = quantity;
		this.description = description;
		this.type = type;
	}

	public String getId() {
		return id;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public EquipmentType getType() {
		return type;
	}
	
	@XmlElement
	public void setType(EquipmentType type) {
		this.type = type;
	}	
	
}
