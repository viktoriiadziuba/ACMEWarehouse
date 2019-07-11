package com.viktoriia.rabbitmq;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
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
		Map map = SerializationUtils.deserialize(body);
		System.out.println("Message Number " + map.get("MessageNumber") + " received.");
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
