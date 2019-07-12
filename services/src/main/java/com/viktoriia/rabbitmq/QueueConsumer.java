package com.viktoriia.rabbitmq;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
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
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {

	public QueueConsumer(String endpointName) throws IOException, TimeoutException {
		super(endpointName);
	}
	
	@Override
	public void run() {
		try {
			channel.basicConsume(endPointName, true, this);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
		QueueMessage message = MessageHandler.deserializeMessage(body);
		MessageHandler.messageBodies.add(message);
		MessageHandler.handle();
	}
	
	
	@Override
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + "registered");
	}

	@Override
	public void handleCancelOk(String consumerTag) {}

	@Override
	public void handleCancel(String consumerTag) throws IOException {}
	
	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {}

	@Override
	public void handleRecoverOk(String consumerTag) {}
	
}
