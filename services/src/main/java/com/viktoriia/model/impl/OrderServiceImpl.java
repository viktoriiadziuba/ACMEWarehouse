package com.viktoriia.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.viktoriia.entity.Order;
import com.viktoriia.model.OrderService;

public class OrderServiceImpl implements OrderService {

	private List<Order> orders = new ArrayList<Order>();

	public void add(Order order) {
		orders.add(order);
	}

	public void update(Order order) {
		orders.add(order);
	}

	public void delete(Order order) {
		orders.remove(order);

	}

	public List<Order> getAll() {
		return orders;
	}

}
