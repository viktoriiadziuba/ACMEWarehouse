package com.viktoriia.rabbitmq;

import java.util.ArrayList;
import java.util.List;

public class MessageBodies {
	
	public static List<QueueMessage> messageBodies = new ArrayList<QueueMessage>();
	
	private MessageBodies() {

	}

	public  synchronized static List<QueueMessage> getMessageBodies() {
		return messageBodies;
	}

	public  synchronized static void setMessageBodies(List<QueueMessage> messageBodies) {
		MessageBodies.messageBodies = messageBodies;
	}
	
	
}
