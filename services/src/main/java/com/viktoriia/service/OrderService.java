package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.OrderStateEntity;

public interface OrderService {

	void add(Order order);
	
	Order getOrderById(int id);

	List<Order> getAllOrders();
	
	List<GoodsEntity> getOrderedGoods(int orderId);
	
	List<OrderStateEntity> getAllOrderStates();
}
