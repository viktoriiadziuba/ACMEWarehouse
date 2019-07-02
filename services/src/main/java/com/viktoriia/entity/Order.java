package com.viktoriia.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8768786933137485826L;

	@Column(name="description", columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "order_state_id")
	private OrderStateEntity state;
	
	@OneToMany(mappedBy = "order", orphanRemoval = true)
	private List<GoodsEntity> goods;
	
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

	public List<GoodsEntity> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsEntity> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Order [description=" + description + ", state=" + state + ", goods=" + goods + 
				", getId()=" + getId()
				+ "]";
	}	

}