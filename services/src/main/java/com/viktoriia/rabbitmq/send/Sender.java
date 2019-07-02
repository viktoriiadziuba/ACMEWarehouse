package com.viktoriia.rabbitmq.send;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.viktoriia.utils.RabbitmqUtil;

public class Sender {

	private static final Logger log = LoggerFactory.getLogger(Sender.class);
	private final static String QUEUE_NAME = "sendObject";
    
	public void sendObject(Object obj) throws Exception {
		 ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        try (Connection connection = factory.newConnection();
	             Channel channel = connection.createChannel()) {
	            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	            
	            log.info("Message is sent");
	            channel.basicPublish("", QUEUE_NAME, null, RabbitmqUtil.convertToByteString(obj).getBytes());
	            log.info(" [x] Sent '" + obj + "'");
	            channel.close();
	            }      
	}
}
