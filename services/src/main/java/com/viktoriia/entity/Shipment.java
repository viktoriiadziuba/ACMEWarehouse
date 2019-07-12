package com.viktoriia.entity;

import java.io.Serializable; 
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shipments")
public class Shipment extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7196079622275175669L;

	@Column(name="date_of_shipment", nullable = false)
	private LocalDate dateOfShipment;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "shipment_state_id")
	private ShipmentStateEntity state;
	
	@Column(nullable = false)
	private int quantity;
	
	public Shipment() {
		
	}

	public LocalDate getDateOfShipment() {
		return dateOfShipment;
	}

	public void setDateOfShipment(LocalDate dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ShipmentStateEntity getState() {
		return state;
	}

	public void setState(ShipmentStateEntity state) {
		this.state = state;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return String.format("[Shipment: "
				+ "id=%d "
				+ "quantity=%d "
				+ "dateOfShipment=%tD "
				+ "description=%s "
				+ "\n" + "%s]", 
				getId(), quantity, dateOfShipment, description, state);
	}
		
}