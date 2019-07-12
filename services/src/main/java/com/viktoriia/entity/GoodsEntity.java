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
	
	@Column(name="description", columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;
	
	@ManyToOne
	@JoinColumn(name = "storage_id")
	private Storage storage;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return String.format("[Goods: "
				+ "id=%d "
				+ "quantity=%d "
				+ "description=%s "
				+ "\n" + "%s "
				+ "\n" + "%s "
				+ "\n" + "%s "
				+ "\n" + "%s",
				getId(), quantity, description, type, shipment, storage, order);
	}	
	
}