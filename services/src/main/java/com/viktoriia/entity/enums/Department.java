package com.viktoriia.entity.enums;

public enum Department {
	
	HR_DEPARTMENT("HR_DEPARTMENT"), 
	SHIPMENT_DEPARTMENT("SHIPMENT_DEPARTMENT"), 
	EQUIPMENT_DEPARTMENT("EQUIPMENT_DEPARTMENT"), 
	MANAGMENT_DEPARTMENT("MANAGMENT_DEPARTMENT"), 
	CHIEF_DEPARTMENT("CHIEF_DEPARTMENT");
	
	private String department;
	
	Department(String department){
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
