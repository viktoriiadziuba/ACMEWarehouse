package com.viktoriia.model.enums;

public enum EquipmentType {

	FORKLIFT("FORKLIFT"), 
	HAND_TRUCKS("HAND_TRUCKS"), 
	SERVICE_CARTS("SERVICE_CARTS"), 
	LADDER("LADDER"), 
	PACKING_TABLE("PACKING_TABLE"), 
	STRATCH_WRAP_MACHINES("STRATCH_WRAP_MACHINES"), 
	SHELF("SHELF"); 
	
	private String fieldType;
	
	EquipmentType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldType() {
		return fieldType;
	}
	
}
