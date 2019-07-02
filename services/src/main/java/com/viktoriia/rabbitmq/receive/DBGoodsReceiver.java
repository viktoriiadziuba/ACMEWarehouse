package com.viktoriia.rabbitmq.receive;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.service.impl.GoodsServiceImpl;
import com.viktoriia.utils.RabbitmqUtil;

public class DBGoodsReceiver {

	private static final Logger log = LoggerFactory.getLogger(DBGoodsReceiver.class);
	private final static String QUEUE_NAME = "sendObject";
	
	public static void receiveGoods() throws Exception {
		log.info("Init receiver...");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(
					String consumerTag,
					Envelope envelope,
					AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				
				String message = new String(body, "UTF-8");
				log.info("Received message...");
				
				GoodsEntity gds = new GoodsEntity();
				
				try {
					gds = (GoodsEntity) RabbitmqUtil.convertFromByteString(message);
					GoodsServiceImpl service = new GoodsServiceImpl();
					/* save to DB*/
					service.add(gds);
					
				} catch(ClassNotFoundException e) {
					log.warn("Something went wrong");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		connection.close();
		channel.close();
		//channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
