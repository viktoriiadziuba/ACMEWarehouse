package com.viktoriia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "storages")
public class Storage extends AbstractEntity {
	
	private static final long serialVersionUID = 6193300568421687362L;
	
	@Column(nullable = false)
	private String address;
	
	public Storage() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("[Storage: "
				+ "id=%d "
				+ "address=%s]", 
				getId(), address);
	}
	
}