package com.viktoriia.rabbitmq;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

public class Producer extends EndPoint {

	public Producer(String endpointName) throws IOException, TimeoutException {
		super(endpointName);
	}
	
	public void sendMessage(Serializable object) throws IOException {
		channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
	}

}
