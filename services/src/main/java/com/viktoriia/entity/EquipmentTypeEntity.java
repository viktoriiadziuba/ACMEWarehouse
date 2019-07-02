package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.EquipmentType;

@Entity
@Table(name = "equipment_type")
public class EquipmentTypeEntity extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 8708465909804829452L;
	
	@Enumerated(EnumType.STRING)
	private EquipmentType type;
	
	@Column(name="description", columnDefinition = "TEXT")
	private String description;
	

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
