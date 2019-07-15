package com.viktoriia.rabbitmq;

import java.util.ArrayList;  

import org.apache.commons.lang3.SerializationUtils;

import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.Storage;
import com.viktoriia.service.impl.EmployeeService;
import com.viktoriia.service.impl.EquipmentService;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.service.impl.ShipmentService;
import com.viktoriia.service.impl.StorageService;

public class MessageHandler implements Runnable {
		
	public static EmployeeService employeeService = new EmployeeService();
	public static EquipmentService equipmentService = new EquipmentService();
	public static OrderService orderService = new OrderService();
	public static ShipmentService shipmentService = new ShipmentService();
	public static GoodsService goodsService = new GoodsService();
	public static StorageService storageService = new StorageService();
	
	public MessageHandler() {
		
	}
	
	@Override
	public void run() {
		handle();
	}
	
	public static ArrayList<QueueMessage> getMessages(){
		return (ArrayList<QueueMessage>) MessageBodies.messageBodies;
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
			if(mes.getClassName().equals(Employee.class.toString())) {
				Employee employee = (Employee) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					employeeService.add(employee);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					employeeService.delete(employee.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					employeeService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					employeeService.getById(employee.getId());
				}
				
			} else if(mes.getClassName().equals(EquipmentEntity.class.toString())) {
				EquipmentEntity equipment = (EquipmentEntity) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					equipmentService.add(equipment);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					equipmentService.delete(equipment.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					equipmentService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					equipmentService.getById(equipment.getId());
				}
				
			} else if(mes.getClassName().equals(Storage.class.toString())) {
				Storage storage = (Storage) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					storageService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					storageService.getById(storage.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					storageService.getWithGoods(storage.getId());
				}
				
			} else if(mes.getClassName().equals(GoodsEntity.class.toString())) {
				GoodsEntity goods = (GoodsEntity) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					goodsService.add(goods);
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					goodsService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					goodsService.getById(goods.getId());
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					goodsService.delete(goods.getId());
				}
				
			} else if(mes.getClassName().equals(Order.class.toString())) {
				Order order = (Order) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					orderService.getAll();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					orderService.getById(order.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					orderService.getWithGoods(order.getId());
				}
				
			} else if(mes.getClassName().equals(Shipment.class.toString())) {
				Shipment shipment = (Shipment) mes.getEntity();
				
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
