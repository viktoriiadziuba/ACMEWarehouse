package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.rabbitmq.receive.DBEquipmentReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.EquipmentService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class EquipmentServiceImpl implements EquipmentService {

	private Sender sender;
	
	public EquipmentServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBEquipmentReceiver.receiveEquipment();
	}
	
	public void add(EquipmentEntity equipment) {
		try {
			sender.sendObject(equipment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(equipment.getType());
		session.save(equipment);
		
		tx1.commit();
		session.close();
	}
	
	public void update(EquipmentEntity equipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(equipment.getType());
		session.update(equipment);
		tx1.commit();
		session.close();

	}

	public void delete(EquipmentEntity equipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(equipment);
		tx1.commit();
		session.close();

	}

	public List<EquipmentEntity> getAll() {
		String query = "SELECT * FROM public.equipment";
		List<EquipmentEntity> equipment = (List<EquipmentEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return equipment;
	}

}