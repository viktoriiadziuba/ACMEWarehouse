package com.viktoriia.service.impl;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.ShipmentStateEntity;
import com.viktoriia.entity.enums.ShipmentState;
import com.viktoriia.service.ShipmentService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class ShipmentServiceImpl implements ShipmentService {
	
	public ShipmentServiceImpl() {
		
	}
	
	public void add(Shipment shipment) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		List<ShipmentStateEntity> states = getAllShipmentStates();
		for(ShipmentStateEntity ship : states) {
			if(shipment.getState().getState().name() == ship.getState().name()) {
				shipment.setState(ship);;
			} 
		}
		session.save(shipment);
		
		tx1.commit();
		session.close();
	}
		
	public List<Shipment> getAllShipments(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(Shipment.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}
	
	public List<ShipmentStateEntity> getAllShipmentStates() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		try
	    {
	        return session.createCriteria(ShipmentStateEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
			session.close();
	    }
	}
	
	public static void main(String[] args) throws Exception {
		ShipmentServiceImpl serv = new ShipmentServiceImpl();
		System.out.println(serv.getShipmentGoods(7));
		
		ShipmentStateEntity state = new ShipmentStateEntity();
		state.setState(ShipmentState.IN_PROGRESS);
		
		Shipment shipment = new Shipment();
		shipment.setDateOfShipment(LocalDate.now());
		shipment.setQuantity(200);
		shipment.setDescription("From USA");
		shipment.setState(state);
		
		//serv.add(shipment);
	}
	
	public static void insertAllShipmentStates() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ShipmentStateEntity state1 = new ShipmentStateEntity();
		state1.setState(ShipmentState.DONE);
		ShipmentStateEntity state2 = new ShipmentStateEntity();
		state2.setState(ShipmentState.IN_PROGRESS);
		ShipmentStateEntity state3 = new ShipmentStateEntity();
		state3.setState(ShipmentState.PLANNED);
		
		session.save(state1);
		session.save(state2);
		session.save(state3);
		
		tx1.commit();
		session.close();
	}

	@Override
	public Shipment getShipmentById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Shipment> shipments = (ArrayList<Shipment>) getAllShipments();
		for(Shipment ship : shipments) {
			if(ship.getId() == id) {
			session.get(Shipment.class, id);
			return ship;
			} 
		}
		
		tx1.commit();
		session.close();
		return null;
	}

	@Override
	public List<GoodsEntity> getShipmentGoods(int shipmentId) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsServiceImpl goodsService = new GoodsServiceImpl();
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) goodsService.getAllGoods();
		ArrayList<GoodsEntity> shipmentGoods = new ArrayList<GoodsEntity>();
		for(GoodsEntity gds : goods) {
			if(gds.getShipment().getId() == shipmentId) {
				shipmentGoods.add(gds);
				return shipmentGoods;
			} 
		}
		tx1.commit();
		session.close();
		return null;
	}

}