package com.viktoriia.rabbitmq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBodies {
	
	private static BlockingQueue<QueueMessage> messageBodies = new LinkedBlockingQueue<QueueMessage>();
	
	private MessageBodies() {

	}

	public  static BlockingQueue<QueueMessage> getMessageBodies() {
		return messageBodies;
	}

	public static void setMessageBodies(BlockingQueue<QueueMessage> messageBodies) {
		MessageBodies.messageBodies = messageBodies;
	}
	

}
