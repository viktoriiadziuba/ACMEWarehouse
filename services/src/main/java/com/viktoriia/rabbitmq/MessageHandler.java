package com.viktoriia.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.Storage;
import com.viktoriia.service.impl.EmployeeServiceImpl;
import com.viktoriia.service.impl.EquipmentServiceImpl;
import com.viktoriia.service.impl.GoodsServiceImpl;
import com.viktoriia.service.impl.OrderServiceImpl;
import com.viktoriia.service.impl.ShipmentServiceImpl;
import com.viktoriia.service.impl.StorageServiceImpl;

public class MessageHandler {
	
	public static List<QueueMessage> messageBodies = new ArrayList<QueueMessage>();
	
	public static EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	public static EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
	public static OrderServiceImpl orderService = new OrderServiceImpl();
	public static ShipmentServiceImpl shipmentService = new ShipmentServiceImpl();
	public static GoodsServiceImpl goodsService = new GoodsServiceImpl();
	public static StorageServiceImpl storageService = new StorageServiceImpl();
	
	private MessageHandler() {
		
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
		Thread handlerThread = new Thread();
		handlerThread.start();
		
		for(QueueMessage mes : messageBodies) {
			System.out.println(mes);
			if(mes.getClassName().toLowerCase().equals("employee")) {
				Employee employee = (Employee) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					employeeService.add(employee);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					employeeService.delete(employee.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					employeeService.getAllEmployees();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					employeeService.getEmployeeById(employee.getId());
				}
				
			} else if(mes.getClassName().toLowerCase().equals("equipment")) {
				EquipmentEntity equipment = (EquipmentEntity) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					equipmentService.add(equipment);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					equipmentService.delete(equipment.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					equipmentService.getAllEquipment();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					equipmentService.getEquipmentById(equipment.getId());
				}
				
			} else if(mes.getClassName().toLowerCase().equals("storage")) {
				Storage storage = (Storage) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					storageService.getAllStorages();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					storageService.getStorageById(storage.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					storageService.getStorageGoods(storage.getId());
				}
			} else if(mes.getClassName().toLowerCase().equals("goods")) {
				GoodsEntity goods = (GoodsEntity) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					goodsService.add(goods);
				} else if(mes.getOperation().equals(CRUDOperation.READ)) {
					goodsService.getAllGoods();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					goodsService.getGoodsById(goods.getId());
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					goodsService.delete(goods.getId());
				}
				
			} else if(mes.getClassName().toLowerCase().equals("order")) {
				Order order = (Order) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					orderService.getAllOrders();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					orderService.getOrderById(order.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					orderService.getOrderedGoods(order.getId());
				}
			} else if(mes.getClassName().toLowerCase().equals("shipment")) {
				Shipment shipment = (Shipment) mes.getEntity();
				
				if(mes.getOperation().equals(CRUDOperation.READ)) {
					shipmentService.getAllShipments();
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_ID)) {
					shipmentService.getShipmentById(shipment.getId());
				} else if(mes.getOperation().equals(CRUDOperation.READ_BY_FIELD)) {
					shipmentService.getShipmentGoods(shipment.getId());
				}
			} else {
				handlerThread.stop();
			}
		}
	}
	
}
