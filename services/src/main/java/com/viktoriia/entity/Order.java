package com.viktoriia.entity;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8768786933137485826L;

	@Column(name="description", columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "order_state_id")
	private OrderStateEntity state;
	
	public Order() {
	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderStateEntity getState() {
		return state;
	}

	public void setState(OrderStateEntity state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return String.format("[Order: "
				+ "id=%d "
				+ "description=%s "
				+ "%s]",
				getId(), description, state);
	}	

}