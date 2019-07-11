package com.viktoriia.service.impl;

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.EquipmentTypeEntity;
import com.viktoriia.entity.enums.EquipmentType;
import com.viktoriia.service.EquipmentService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class EquipmentServiceImpl implements Serializable, EquipmentService {
	
	private static final long serialVersionUID = -3439969593531677905L;

	public EquipmentServiceImpl() {
		
	}
	
	@Override
	public void add(EquipmentEntity equipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		List<EquipmentTypeEntity> equipments = getAllEquipmentTypes();
		for(EquipmentTypeEntity eqv : equipments) {
			if(equipment.getType().getType().name() == eqv.getType().name()) {
				equipment.setType(eqv);
			} 
		}
		session.save(equipment);
		
		tx1.commit();
		session.close();
	}
	
	@Override
	public void delete(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		EquipmentServiceImpl service = new EquipmentServiceImpl();
		ArrayList<EquipmentEntity> equipments = (ArrayList<EquipmentEntity>) service.getAllEquipment();
		for(EquipmentEntity eqv : equipments) {
			if(eqv.getId() == id) {
				session.delete(eqv);
			} 
		}
		
		tx1.commit();
		session.close();
	}
	
	@Override
	public EquipmentEntity getEquipmentById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		EquipmentServiceImpl service = new EquipmentServiceImpl();
		ArrayList<EquipmentEntity> equips = (ArrayList<EquipmentEntity>) service.getAllEquipment();
		for(EquipmentEntity eqv : equips) {
			if(eqv.getId() == id) {
			session.get(EquipmentEntity.class, id);
			return eqv;
			}
		}
		
		tx1.commit();
		session.close();
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		EquipmentTypeEntity type = new EquipmentTypeEntity();
		type.setType(EquipmentType.HAND_TRUCKS);
		
		EquipmentEntity equipment = new EquipmentEntity();
		equipment.setType(type);
		equipment.setQuantity(200);
		
		EquipmentServiceImpl service = new EquipmentServiceImpl();
		//service.getEquipmentById(2);
		System.out.println(service.getEquipmentById(2));
		
	}

	@Override
	public List<EquipmentEntity> getAllEquipment(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(EquipmentEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}

	@Override
	public List<EquipmentTypeEntity> getAllEquipmentTypes() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		try
	    {
	        return session.createCriteria(EquipmentTypeEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}

	public static void insertAllEquipmentTypes() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		EquipmentTypeEntity type1 = new EquipmentTypeEntity();
		type1.setType(EquipmentType.FORKLIFT);
		EquipmentTypeEntity type2 = new EquipmentTypeEntity();
		type2.setType(EquipmentType.HAND_TRUCKS);
		EquipmentTypeEntity type3 = new EquipmentTypeEntity();
		type3.setType(EquipmentType.LADDER);
		EquipmentTypeEntity type4 = new EquipmentTypeEntity();
		type4.setType(EquipmentType.PACKING_TABLE);
		EquipmentTypeEntity type5 = new EquipmentTypeEntity();
		type5.setType(EquipmentType.SERVICE_CARTS);
		EquipmentTypeEntity type6 = new EquipmentTypeEntity();
		type6.setType(EquipmentType.SHELF);
		EquipmentTypeEntity type7 = new EquipmentTypeEntity();
		type7.setType(EquipmentType.STRATCH_WRAP_MACHINES);
		
		session.save(type1);
		session.save(type2);
		session.save(type3);
		session.save(type4);
		session.save(type5);
		session.save(type6);
		session.save(type7);
		
		tx1.commit();
		session.close();
	}
}