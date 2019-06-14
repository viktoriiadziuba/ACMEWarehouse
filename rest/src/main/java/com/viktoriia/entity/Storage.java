package com.viktoriia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Storage {

	private String id;

	private String address;
	private String capacity;

	public Storage() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
