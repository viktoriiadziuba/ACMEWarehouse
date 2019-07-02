package com.viktoriia.entity.enums;

public enum GoodsType {
	
	CLOTHES("CLOTHES"),
	ACCESSORIES("ACCESSORIES"), 
	SHOES("SHOES"),
	FURNITURE("FURNITURE"), 
	HOUSEHOLD_EQUIPMENT("HOUSEHOLD_EQUIPMENT");
	
	private String type;
	
	GoodsType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
