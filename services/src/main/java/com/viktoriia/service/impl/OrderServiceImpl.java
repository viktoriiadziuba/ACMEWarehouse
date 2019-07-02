package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.Order;
import com.viktoriia.rabbitmq.receive.DBOrderReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.OrderService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class OrderServiceImpl implements OrderService {

	private Sender sender;
	
	public OrderServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBOrderReceiver.receiveOrder();
	}
	
	public void add(Order order) {
		try {
			sender.sendObject(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(order.getState());
		session.save(order.getGoods());
		session.save(order);

		
		tx1.commit();
		session.close();
	}
	
	public void update(Order order) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(order.getState());
		session.save(order.getGoods());
		session.update(order);
		tx1.commit();
		session.close();

	}

	public void delete(Order order) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(order);
		tx1.commit();
		session.close();

	}

	public List<Order> getAll() {
		String query = "SELECT * FROM public.order";
		List<Order> orders = (List<Order>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return orders;
	}
	
}