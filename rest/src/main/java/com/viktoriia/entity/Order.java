package com.viktoriia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {

	private String id;

	private String type;
	private String description;

	public Order() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
