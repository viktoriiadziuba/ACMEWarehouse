package com.viktoriia.service.impl;

 import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Storage;
import com.viktoriia.service.AbstractService;
import com.viktoriia.service.WithGoodsService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class StorageService extends AbstractService implements WithGoodsService<Storage> {
	
	public StorageService() {
		
	}

	public void add(Storage storage) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(storage);
				
		tx1.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
				
		ArrayList<Storage> storages = (ArrayList<Storage>) getAll();
		for(Storage str : storages) {
			if(str.getId() == id) {
			session.delete(str);
			} 
		}
		tx1.commit();
		session.close();
	}

	@Override
	public List<Storage> getAll(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(Storage.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}

	@Override
	public Storage getById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Storage> storages = (ArrayList<Storage>) getAll();
		for(Storage stor : storages) {
			if(stor.getId() == id) {
			session.get(Storage.class, id);
			return stor;
			} 
		}
		tx1.commit();
		session.close();
		return null;
	}


	@Override
	public List<GoodsEntity> getWithGoods(int storageId) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsService goodsService = new GoodsService();
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) goodsService.getAll();
		ArrayList<GoodsEntity> storageGoods = new ArrayList<GoodsEntity>();
		for(GoodsEntity gds : goods) {
			if(gds.getStorage().getId() == storageId) {
				storageGoods.add(gds);
				return storageGoods;
			} 
		}
		tx1.commit();
		session.close();
		return null;
	}
}

