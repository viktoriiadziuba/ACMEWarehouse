package com.viktoriia.entity.enums;

public enum OrderState {
	
	PLANNED("PLANNED"), 
	IN_PROGRESS("IN_PROGRESS"), 
	DONE("DONE");
	
	private String state;
	
	OrderState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
