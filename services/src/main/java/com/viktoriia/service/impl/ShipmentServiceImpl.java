package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.Shipment;
import com.viktoriia.rabbitmq.receive.DBShipmentReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.ShipmentService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class ShipmentServiceImpl implements ShipmentService {

	private Sender sender;
	
	public ShipmentServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBShipmentReceiver.receiveShipment();
	}
	
	public void add(Shipment shipment) {
		try {
			sender.sendObject(shipment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(shipment.getState());
		session.save(shipment.getGoods());
		session.save(shipment);
		
		tx1.commit();
		session.close();
	}
	
	public void update(Shipment shipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(shipment.getState());
		session.update(shipment);
		tx1.commit();
		session.close();

	}

	public void delete(Shipment shipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(shipment);
		tx1.commit();
		session.close();

	}

	public List<Shipment> getAll() {
		String query = "SELECT * FROM public.shipment";
		List<Shipment> shipments = (List<Shipment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return shipments;
	}

}