package com.viktoriia.service;

import java.util.Optional;

import com.viktoriia.entity.AbstractEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.Storage;
import com.viktoriia.entity.User;
import com.viktoriia.service.impl.EmployeeService;
import com.viktoriia.service.impl.EquipmentService;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.service.impl.ShipmentService;
import com.viktoriia.service.impl.StorageService;
import com.viktoriia.service.impl.UserService;

public class ServiceFactory {
	
	public static  AbstractService getService(AbstractEntity entity) {
		AbstractService service;
		
		if(entity instanceof Employee) {
			service = new EmployeeService();
		} else if(entity instanceof EquipmentEntity) {
			service = new EquipmentService();
		} else if(entity instanceof GoodsEntity) {
			service = new GoodsService();
		} else if(entity instanceof Order) {
			service = new OrderService();
		} else if(entity instanceof Shipment) {
			service = new ShipmentService();
		} else if(entity instanceof Storage) {
			service = new StorageService();
		} else if (entity instanceof User) {
			service = new UserService();
		} else {
			throw new NullPointerException("Service not found");
		}
		return service;
	}

}
