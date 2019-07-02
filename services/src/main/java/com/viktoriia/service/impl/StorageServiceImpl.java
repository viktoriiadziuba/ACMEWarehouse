package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.Storage;
import com.viktoriia.rabbitmq.receive.DBStorageReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.StorageService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class StorageServiceImpl implements StorageService {

	private Sender sender;
	
	public StorageServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBStorageReceiver.receiveStorage();
	}
	
	public void add(Storage storage) {
		try {
			sender.sendObject(storage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(storage.getGoods());
		session.save(storage);

		
		tx1.commit();
		session.close();
	}
	
	public void update(Storage storage) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(storage.getGoods());
		session.update(storage);
		tx1.commit();
		session.close();

	}

	public void delete(Storage storage) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(storage);
		tx1.commit();
		session.close();

	}

	public List<Storage> getAll() {
		String query = "SELECT * FROM public.storage";
		List<Storage> storages = (List<Storage>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return storages;
	}

}

