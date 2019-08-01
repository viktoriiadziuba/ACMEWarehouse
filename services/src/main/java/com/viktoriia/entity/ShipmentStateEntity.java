package com.viktoriia.entity;

import java.io.Serializable; 

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.ShipmentState;

@Entity
@Table(name = "shipment_state")
public class ShipmentStateEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4784002534132962929L;
	
	@Enumerated(EnumType.STRING)
	private ShipmentState state;

	
	public ShipmentState getState() {
		return state;
	}

	public void setState(ShipmentState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("	[ShipmentState: "
				+ "id=%d "
				+ "state=%s]", 
				getId(), getState());
	}
	
	
}
