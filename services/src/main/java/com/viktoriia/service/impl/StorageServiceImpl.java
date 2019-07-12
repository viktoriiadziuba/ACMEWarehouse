package com.viktoriia.service.impl;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.GoodsTypeEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.OrderStateEntity;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.ShipmentStateEntity;
import com.viktoriia.entity.Storage;
import com.viktoriia.entity.enums.GoodsType;
import com.viktoriia.entity.enums.OrderState;
import com.viktoriia.entity.enums.ShipmentState;
import com.viktoriia.service.StorageService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class StorageServiceImpl implements StorageService {
	
	public StorageServiceImpl() {
		
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
				
		ArrayList<Storage> storages = (ArrayList<Storage>) getAllStorages();
		for(Storage str : storages) {
			if(str.getId() == id) {
			session.delete(str);
			} 
		}
		tx1.commit();
		session.close();
	}

	@Override
	public List<Storage> getAllStorages(){  
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
	public Storage getStorageById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Storage> storages = (ArrayList<Storage>) getAllStorages();
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
	public List<GoodsEntity> getStorageGoods(int storageId) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsServiceImpl goodsService = new GoodsServiceImpl();
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) goodsService.getAllGoods();
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

