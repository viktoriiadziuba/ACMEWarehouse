package com.viktoriia.rabbitmq;

import java.util.concurrent.BlockingQueue; 

import org.apache.commons.lang3.SerializationUtils;

import com.viktoriia.entity.Employee;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.User;
import com.viktoriia.service.ServiceFactory;
import com.viktoriia.service.impl.EmployeeService;
import com.viktoriia.service.impl.EquipmentService;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.service.impl.UserService;

public class MessageHandler implements Runnable {
		
	public MessageHandler() {
		
	}

	@Override
	public void run() {
		while(!getMessages().isEmpty()) {
			QueueMessage message;
			try {
				message = getMessages().take();
				handle(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
		
	public static BlockingQueue<QueueMessage> getMessages(){
		return MessageBodies.getMessageBodies();
	}

	public static byte[] serializeMessage(QueueMessage message) {
		byte[] serializedMessage = SerializationUtils.serialize(message);
		return serializedMessage;
	}
	
	public static QueueMessage deserializeMessage(byte[] message) {
		QueueMessage deserializedMessage = SerializationUtils.deserialize(message);
		return deserializedMessage;
	}
	
	private static void handle(QueueMessage mes) {
			if(mes.getClassEntity().equals(Employee.class)) {
				Employee employee = (Employee) mes.getEntity();
				EmployeeService employeeService = (EmployeeService) ServiceFactory.getService(employee);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					employeeService.add(employee);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					employeeService.delete(employee.getId());
				}
				
			} else if(mes.getClassEntity().equals(EquipmentEntity.class)) {
				EquipmentEntity equipment = (EquipmentEntity) mes.getEntity();
				EquipmentService equipmentService = (EquipmentService) ServiceFactory.getService(equipment);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					equipmentService.add(equipment);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					equipmentService.delete(equipment.getId());
				}
				
			} else if(mes.getClassEntity().equals(GoodsEntity.class)) {
				GoodsEntity goods = (GoodsEntity) mes.getEntity();
				GoodsService goodsService = (GoodsService) ServiceFactory.getService(goods);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					goodsService.add(goods);
				} else if(mes.getOperation().equals(CRUDOperation.DELETE)) {
					goodsService.delete(goods.getId());
				}
				
			} else if(mes.getClassEntity().equals(User.class)) {
				User user = (User) mes.getEntity();
				UserService userService = (UserService) ServiceFactory.getService(user);
				
				if(mes.getOperation().equals(CRUDOperation.CREATE)) {
					userService.signup(user);
				}
			}
	}
	
}
