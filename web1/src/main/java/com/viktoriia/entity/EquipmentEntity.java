package com.viktoriia.entity;

public class EquipmentEntity {

	private String id;

	private String type;

	public EquipmentEntity(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
