package com.viktoriia.rabbitmq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {
	
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	
	public Properties getPropValue() throws IOException {
		Properties prop = new Properties();
		String propFileName = "rabbitmq.properties";
		
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {		
			prop.load(inputStream);
		}		
		return prop;
	}
	
	public EndPoint(String endpointName) throws IOException, TimeoutException {
		this.endPointName = endpointName;
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(getPropValue().getProperty("host"));
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(endpointName, false, false, false, null);
	}

	 public void close() throws IOException, TimeoutException {
         this.channel.close();
         this.connection.close();
     }
}
