package com.viktoriia.entity.enums;

public enum UserRole {

	ADMIN("ADMIN"),
	CHIEF("CHIEF"), 
	SHIPMENT_MANAGER("SHIPMENT_MANAGER"), 
	EQUIPMENT_MANADER("EQUIPMENT_MANADER"), 
	HR_MANAGER("HR_MANAGER"), 
	STORAGE_SUPERVIZOR("STORAGE_SUPERVIZOR");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
