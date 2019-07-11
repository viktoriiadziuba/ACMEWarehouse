package com.viktoriia.utils;

import java.io.ByteArrayInputStream; 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.viktoriia.entity.AbstractEntity;

public class RabbitmqUtil {
	
	public final static String QUEUE_NAME = "sendObjectToDB";
	
	public RabbitmqUtil() {
		
	}
	
	public static String convertToByteString(AbstractEntity entity) throws IOException {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(baos)) {
			out.writeObject(entity);
			final byte[] byteArray = baos.toByteArray();
			return Base64.getEncoder().encodeToString(byteArray);
		}
	}
	
	public static Object convertFromByteString(String byteString) throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(byteString);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bais)) {
			return in.readObject();
		}
	}

	public static Channel getChannelToDB() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		return channel;
	}
}
