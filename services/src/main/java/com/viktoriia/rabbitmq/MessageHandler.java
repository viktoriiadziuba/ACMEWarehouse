package com.viktoriia.rabbitmq;

import java.util.ArrayList;   

import org.apache.commons.lang3.SerializationUtils;

import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.Storage;
import com.viktoriia.service.ServiceFactory;
import com.viktoriia.service.impl.EmployeeService;
import com.viktoriia.service.impl.EquipmentService;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.service.impl.ShipmentService;
import com.viktoriia.service.impl.StorageService;

public class MessageHandler implements Runnable {
	
	public MessageHandler() {
		
	}
	
	@Override
	public void run() {
		handle();
	}
	
	public static ArrayList<QueueMessage> getMessages(){
		ArrayList<QueueMessage> messages = new ArrayList<QueueMessage>();
		MessageBodies.getMessageBodies().drainTo(messages);
		return messages;
	}

	public static byte[] serializeMessage(QueueMessage message) {
		byte[] serializedMessage = SerializationUtils.serialize(message);
		return serializedMessage;
	}
	
	public static QueueMessage deserializeMessage(byte[] message) {
		QueueMessage deserializedMessage = SerializationUtils.deserialize(message);
		return deserializedMessage;
	}
	
	public static void handle() {
		
		for(QueueMessage mes : getMessages()) {
			System.out.println(mes);
	
			if(mes.getClassEntity().equals(Employee.class)) {
				Employee employee = (Employee) mes.getEntity();
				EmployeeService employeeService = (EmployeeService) ServiceFactory.getService(employee);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					employeeService.add(employee);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					employeeService.delete(employee.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					employeeService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					employeeService.getById(employee.getId());
				}
				
			} else if(mes.getClassEntity().equals(EquipmentEntity.class)) {
				EquipmentEntity equipment = (EquipmentEntity) mes.getEntity();
				EquipmentService equipmentService = (EquipmentService) ServiceFactory.getService(equipment);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					equipmentService.add(equipment);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					equipmentService.delete(equipment.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					equipmentService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					equipmentService.getById(equipment.getId());
				}
				
			} else if(mes.getClassEntity().equals(Storage.class)) {
				Storage storage = (Storage) mes.getEntity();
				StorageService storageService = (StorageService) ServiceFactory.getService(storage);
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					storageService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					storageService.getById(storage.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					storageService.getWithGoods(storage.getId());
				}
				
			} else if(mes.getClassEntity().equals(GoodsEntity.class)) {
				GoodsEntity goods = (GoodsEntity) mes.getEntity();
				GoodsService goodsService = (GoodsService) ServiceFactory.getService(goods);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					goodsService.add(goods);
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					goodsService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					goodsService.getById(goods.getId());
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					goodsService.delete(goods.getId());
				}
				
			} else if(mes.getClassEntity().equals(Order.class)) {
				Order order = (Order) mes.getEntity();
				OrderService orderService = (OrderService) ServiceFactory.getService(order);
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					orderService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					orderService.getById(order.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					orderService.getWithGoods(order.getId());
				}
				
			} else if(mes.getClassEntity().equals(Shipment.class)) {
				Shipment shipment = (Shipment) mes.getEntity();
				ShipmentService shipmentService = (ShipmentService) ServiceFactory.getService(shipment);
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					shipmentService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					shipmentService.getById(shipment.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					shipmentService.getWithGoods(shipment.getId());
				}
			} 
		}
	}
}
