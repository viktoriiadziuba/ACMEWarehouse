package com.viktoriia.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shipment")
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
	
	@OneToMany(mappedBy = "shipment", orphanRemoval = true)
	private List<GoodsEntity> goods;

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

	public List<GoodsEntity> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsEntity> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Shipment [dateOfShipment=" + dateOfShipment + ", description=" + description + ", state=" + state
				+ ", quantity=" + quantity + ", goods=" + goods + 
				", getId()=" + getId() + "]";
	}
		
}