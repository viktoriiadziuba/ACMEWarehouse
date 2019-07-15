package com.viktoriia.service.impl;

import java.util.ArrayList; 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.OrderStateEntity;
import com.viktoriia.entity.enums.OrderState;
import com.viktoriia.service.WithGoodsService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class OrderService implements WithGoodsService<Order> {
	
	public OrderService() {
		
	}
	
	public void add(Order order) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		List<OrderStateEntity> states = getAllOrderStates();
		for(OrderStateEntity or : states) {
			if(order.getState().getState().name() == or.getState().name()) {
				order.setState(or);;
			} 
		}
		session.save(order);

		
		tx1.commit();
		session.close();
	}
	
	@Override
	public Order getById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Order> orders = (ArrayList<Order>) getAll();
		for(Order ord : orders) {
			if(ord.getId() == id) {
			session.get(Order.class, id);
			return ord;
			} 
		}
		
		tx1.commit();
		session.close();
		return null;
	}
	
	@Override
	public List<Order> getAll(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(Order.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}
	
	@Override
	public List<GoodsEntity> getWithGoods(int orderId) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsService goodsService = new GoodsService();
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) goodsService.getAll();
		ArrayList<GoodsEntity> orderedGoods = new ArrayList<GoodsEntity>();
		for(GoodsEntity gds : goods) {
			if(gds.getOrder().getId() == orderId) {
				orderedGoods.add(gds);
				return orderedGoods;
			} 
		}
		tx1.commit();
		session.close();
		return null;
	}

	public List<OrderStateEntity> getAllOrderStates() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		try
	    {
	        return session.createCriteria(OrderStateEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}
	
	public static void insertAllOrderStates() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		OrderStateEntity state1 = new OrderStateEntity();
		state1.setState(OrderState.DONE);
		OrderStateEntity state2 = new OrderStateEntity();
		state2.setState(OrderState.IN_PROGRESS);
		OrderStateEntity state3 = new OrderStateEntity();
		state3.setState(OrderState.PLANNED);
		
		session.save(state1);
		session.save(state2);
		session.save(state3);
		
		tx1.commit();
		session.close();
	}
	
}