package org.xarch.reliable.service;

import org.xarch.reliable.config.RabbitConfig;
import org.xarch.reliable.utils.BaseResultTools;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = RabbitConfig.clearCenterQueue)
public class ClearReceiver {
	
	@Autowired
	private ClearServer clearServer;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	public void receiveMessage(String datastr) {
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		clearServer.parseClearData((Map<String, Object>)data);
		return;
	}
	
}
