package com.viktoriia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EquipmentEntity {

	private String id;

	private String type;

	public EquipmentEntity() {
		
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

}
