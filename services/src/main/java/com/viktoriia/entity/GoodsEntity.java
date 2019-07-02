package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class GoodsEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -394858256356955249L;

	@ManyToOne
	@JoinColumn(name = "goods_type_id")
	private GoodsTypeEntity type;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "goods_id")
	private Shipment shipment;
	
	@ManyToOne
	@JoinColumn(name = "goods_id", insertable=false, updatable=false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "goods_id", insertable=false, updatable=false)
	private Storage storage;
	
	public GoodsEntity() {
		
	}

	public GoodsTypeEntity getType() {
		return type;
	}

	public void setType(GoodsTypeEntity type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		return "GoodsEntity [type=" + type + ", quantity=" + quantity + ", shipment=" + shipment + ", order=" + order
				+ ", storage=" + storage + 
				", getId()=" + getId() + "]";
	}		
	
}