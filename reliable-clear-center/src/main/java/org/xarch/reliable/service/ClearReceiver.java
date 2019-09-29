package org.xarch.reliable.service;

import org.xarch.reliable.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = RabbitConfig.clearCenterQueue)
public class ClearReceiver {

	private static final Logger logger = LoggerFactory.getLogger(ClearReceiver.class);
	
	@RabbitHandler
	public void receiveMessage(String message) {
		logger.info("ClearReceiver[] 消息 <" + message + ">");
	}
	
}
