package com.viktoriia.entity;

public class GoodsEntity {

	private String id;

	private String type;
	private String description;

	public GoodsEntity(String id, String type, String description) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
