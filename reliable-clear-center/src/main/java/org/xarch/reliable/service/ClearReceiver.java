package org.xarch.reliable.service;

import org.xarch.reliable.config.RabbitConfig;
import org.xarch.reliable.utils.BaseResultTools;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = RabbitConfig.clearCenterQueue)
public class ClearReceiver {

	private static final Logger logger = LoggerFactory.getLogger(ClearReceiver.class);
	
	@Autowired
	private ClearServer clearServer;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	public void receiveMessage(String datastr) {
		logger.info("ClearReceiver 接受消息 : " + datastr);
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		clearServer.parseClearData((Map<String, Object>)data);
		return;
	}
	
}
