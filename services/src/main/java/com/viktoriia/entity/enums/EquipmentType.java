package com.viktoriia.entity.enums;

public enum EquipmentType {
	
	FORKLIFT("FORKLIFT"), 
	HAND_TRUCKS("HAND_TRUCKS"), 
	SERVICE_CARTS("SERVICE_CARTS"), 
	LADDER("LADDER"), 
	PACKING_TABLE("PACKING_TABLE"), 
	STRATCH_WRAP_MACHINES("STRATCH_WRAP_MACHINES"), 
	SHELF("SHELF");	

	private String type;
	
	EquipmentType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
