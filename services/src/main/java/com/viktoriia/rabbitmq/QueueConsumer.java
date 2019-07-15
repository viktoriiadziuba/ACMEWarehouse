package com.viktoriia.rabbitmq;

import java.io.IOException; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {
	
	static final int MAX_T = 2;
	ExecutorService pool;

	public QueueConsumer(String endpointName) throws IOException, TimeoutException {
		super(endpointName);
		
		pool = Executors.newFixedThreadPool(MAX_T);
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
		MessageHandler.getMessages().add(message);
		pool.execute(new MessageHandler());
		pool.shutdown();
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
