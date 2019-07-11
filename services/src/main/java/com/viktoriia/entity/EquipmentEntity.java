package com.viktoriia.entity;

import java.io.Serializable; 

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class EquipmentEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8229918073559862340L;

	@Column(name="quantity", nullable = false)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "equipment_type_id")
	private EquipmentTypeEntity type;
	

	public EquipmentEntity() {
		
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public EquipmentTypeEntity getType() {
		return type;
	}


	public void setType(EquipmentTypeEntity type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return String.format("[Equipment: "
				+ "id=%d "
				+ "quantity=%d "
				+ "\n" + "%s]", 
				getId(), quantity, type);       
	}	
	
}