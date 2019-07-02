package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.OrderState;

@Entity
@Table(name = "order_state")
public class OrderStateEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4556015651619437868L;
	
	@Enumerated(EnumType.STRING)
	private OrderState state;

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

}
