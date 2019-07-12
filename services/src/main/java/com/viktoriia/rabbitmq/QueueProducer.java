package com.viktoriia.rabbitmq;

import java.io.IOException; 
import java.util.concurrent.TimeoutException;

public class QueueProducer extends EndPoint {
	
	public static final String exchange = "";
	
	public QueueProducer(String endpointName) throws IOException, TimeoutException {
		super(endpointName);
	}
	
	public void sendMessage(QueueMessage object) throws IOException {
		channel.basicPublish(exchange, endPointName, null, MessageHandler.serializeMessage(object));
	}

}
