package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.Order;

@XmlRootElement
public class OrderService {

	public static List<Order> orders = new ArrayList<>();

	public void add(Order oder) {
		orders.add(oder);
	}

	@XmlElement(name = "listOrders")
	public List<Order> getAll() {
		return orders;
	}
}
