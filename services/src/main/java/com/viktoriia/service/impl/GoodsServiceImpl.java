package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.rabbitmq.receive.DBGoodsReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.GoodsService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class GoodsServiceImpl implements GoodsService {

	private Sender sender;
	
	public GoodsServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBGoodsReceiver.receiveGoods();
	}
	
	public void add(GoodsEntity goods) {
		try {
			sender.sendObject(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(goods.getType());
		session.save(goods.getShipment());
		session.save(goods.getStorage());
		session.save(goods.getOrder());
		session.save(goods);
		
		tx1.commit();
		session.close();
	}
	
	public void update(GoodsEntity goods) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(goods.getType());
		session.update(goods);
		tx1.commit();
		session.close();

	}

	public void delete(GoodsEntity goods) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(goods);
		tx1.commit();
		session.close();

	}

	public List<GoodsEntity> getAll() {
		String query = "SELECT * FROM public.goods";
		List<GoodsEntity> goods = (List<GoodsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return goods;
	}

}