package com.viktoriia.rabbitmq;

import java.io.FileNotFoundException;
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
	InputStream inputStream;
	String host = "";
	
	public String getPropValue() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "rabbitmq.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if(inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file " + propFileName + " not found");
			}
			host = prop.getProperty("host");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return host;
	}
	
	public EndPoint(String endpointName) throws IOException, TimeoutException {
		this.endPointName = endpointName;
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(endpointName, false, false, false, null);
	}

	 public void close() throws IOException, TimeoutException {
         this.channel.close();
         this.connection.close();
     }
}
