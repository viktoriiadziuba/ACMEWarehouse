package com.viktoriia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.enums.EquipmentType;

@XmlRootElement
@Entity
@Table(name = "equipment")
public class EquipmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int quantity;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private EquipmentType type;
	

	public EquipmentEntity() {
		
	}

	public int getId() {
		return id;
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public EquipmentType getType() {
		return type;
	}

	public void setType(EquipmentType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}