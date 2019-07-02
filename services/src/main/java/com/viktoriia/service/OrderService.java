package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.Order;

public interface OrderService {

	void add(Order order);

	void update(Order order);

	void delete(Order order);

	List<Order> getAll();

}
