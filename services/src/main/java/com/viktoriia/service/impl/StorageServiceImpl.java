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

	@Override
	public void add(Storage storage) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(storage);
				
		tx1.commit();
		session.close();
	}

	@Override
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
	
	public static void main(String[] args) {
		Storage storage = new Storage();
		storage.setAddress("Number 1");
		
		GoodsTypeEntity type = new GoodsTypeEntity();
		type.setType(GoodsType.CLOTHES);
		
		ShipmentStateEntity shipState = new ShipmentStateEntity();
		shipState.setState(ShipmentState.IN_PROGRESS);
		
		Shipment shipment = new Shipment();
		shipment.setDateOfShipment(LocalDate.now());
		shipment.setDescription("From USA");
		shipment.setQuantity(400);
		shipment.setState(shipState);
		
		OrderStateEntity orderState = new OrderStateEntity();
		orderState.setState(OrderState.PLANNED);
		
		Order order = new Order();
		order.setDescription("To ... address");
		order.setState(orderState);
		
		GoodsEntity goods = new GoodsEntity();
		goods.setDescription("T-shirts");
		goods.setQuantity(300);
		goods.setType(type);
		goods.setStorage(storage);
		goods.setShipment(shipment);
		goods.setOrder(order);
		
		StorageServiceImpl service = new StorageServiceImpl();
		//service.addStorageWithGoods(goods);
		//service.deleteStorageWithGoods(3);
		System.out.println(service.getStorageGoods(5));
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

	@Override
	public GoodsEntity addStorageWithGoods(GoodsEntity entity) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsEntity goods = new GoodsEntity();
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		ShipmentServiceImpl shipmentService = new ShipmentServiceImpl();
		GoodsServiceImpl goodsService = new GoodsServiceImpl();
		orderService.add(entity.getOrder());
		shipmentService.add(entity.getShipment());
		
		List<GoodsTypeEntity> goodsTypes = goodsService.getAllGoodsTypes();
		for(GoodsTypeEntity type : goodsTypes) {
			if(entity.getType().getType().name() == type.getType().name()) {
				goods.setType(type);
			}
		}
		
		goods.setDescription(entity.getDescription());
		goods.setQuantity(entity.getQuantity());
		goods.setOrder(entity.getOrder());
		goods.setShipment(entity.getShipment());
		goods.setShipment(entity.getShipment());
		goods.setStorage(entity.getStorage());
		
		session.save(goods.getStorage());
		session.save(goods);
		
		tx1.commit();
		session.close();
		return null;
	}

	@Override
	public void deleteStorageWithGoods(int storageId) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		GoodsServiceImpl goodsService = new GoodsServiceImpl();
		ArrayList<GoodsEntity> goods = (ArrayList<GoodsEntity>) goodsService.getAllGoods();
		for(GoodsEntity gds : goods) {
			session.delete(gds);
		}
		ArrayList<Storage> storages = (ArrayList<Storage>) getAllStorages();
		for(Storage str : storages) {
			if(str.getId() == storageId) {
				session.delete(str);
			}
		}
		tx1.commit();
		session.close();		
	}

}

