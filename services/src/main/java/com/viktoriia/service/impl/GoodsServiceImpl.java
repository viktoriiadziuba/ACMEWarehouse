package com.viktoriia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.GoodsTypeEntity;
import com.viktoriia.entity.enums.GoodsType;
import com.viktoriia.service.GoodsService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class GoodsServiceImpl implements GoodsService {
	
	public GoodsServiceImpl() {
		
	}
	
	@Override
	public GoodsEntity getGoodsById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) getAllGoods();
		for(GoodsEntity gds : goods) {
			if(gds.getId() == id) {
			session.get(GoodsEntity.class, id);
			return gds;
			} else {
				System.out.println("There isn't such Employee");
			}
		}
		
		tx1.commit();
		session.close();
		return null;
	}

	@Override
	public List<GoodsEntity> getAllGoods(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(GoodsEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}

	@Override
	public List<GoodsTypeEntity> getAllGoodsTypes() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		try
	    {
	        return session.createCriteria(GoodsTypeEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}

	public static void insertAllGoodsTypes() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsTypeEntity type1 = new GoodsTypeEntity();
		type1.setType(GoodsType.ACCESSORIES);
		GoodsTypeEntity type2 = new GoodsTypeEntity();
		type2.setType(GoodsType.CLOTHES);
		GoodsTypeEntity type3 = new GoodsTypeEntity();
		type3.setType(GoodsType.FURNITURE);
		GoodsTypeEntity type4 = new GoodsTypeEntity();
		type4.setType(GoodsType.HOUSEHOLD_EQUIPMENT);
		GoodsTypeEntity type5 = new GoodsTypeEntity();
		type5.setType(GoodsType.SHOES);
		
		session.save(type1);
		session.save(type2);
		session.save(type3);
		session.save(type4);
		session.save(type5);
		
		tx1.commit();
		session.close();
	}
}