package com.viktoriia.entity;

public class Storage {

	private String id;

	private String address;
	private String capacity;

	public Storage(String id, String address, String capacity) {
		super();
		this.id = id;
		this.address = address;
		this.capacity = capacity;
	}

	public String getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

}
