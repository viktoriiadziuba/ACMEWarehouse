package com.viktoriia.model.enums;

public enum ShipmentState {

	PLANNED("PLANNED"), 
	IN_PROGRESS("IN_PROGRESS"), 
	DONE("DONE");
	
	private String fieldState;
	
	ShipmentState(String fieldState) {
		this.fieldState = fieldState;
	}

	public String getFieldState() {
		return fieldState;
	}
}
