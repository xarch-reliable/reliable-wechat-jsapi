package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.ClearServer;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.service.thread.ThreadPool;
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class ClearServerImpl implements ClearServer {
	
	private static final Logger logger = LoggerFactory.getLogger(ClearServerImpl.class);
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private FeignDataManager feignDataManager;
	
	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
	@Override
	public Map<String, Object> parseClearData(Map<String, Object> data) {
		Map<String, Object> reliableMap = BaseResultTools.ObjectToMap(data.get("ReliableMap"));
		Map<String, Object> unreliableMap = BaseResultTools.ObjectToMap(data.get("UnReliableMap"));
		if(reliableMap==null || unreliableMap==null) {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "清分数据格式错误");
			return resmap;
		}
		return ClearProcess(reliableMap, unreliableMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> ClearProcess(Map<String, Object> ReliableMap, Map<String, Object> UnReliableMap) {
		
		Integer sumTotalFee = new Integer(0);
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		sendmap.put("xrdataction", "getOrderTotalFee");
		
		for (Entry<String, Object> entry: UnReliableMap.entrySet()) {
			Map<String, Object> datatmp = new HashMap<String, Object>();
			datatmp.put("out_trade_no", entry.getValue());
			sendmap.put("data", datatmp);
			Map<String, Object> resmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
			logger.info("[total_fee] " + (String)resmap.get("total_fee"));
			sumTotalFee += Integer.valueOf((String)resmap.get("total_fee"));
		}
		
		for (Entry<String, Object> entry: ReliableMap.entrySet()) {
			Map<String, Object> refundmap = new HashMap<String, Object>();
			refundmap.put("out_trade_no", (String)entry.getValue());
			refundmap.put("out_refund_no", String.valueOf(System.currentTimeMillis()));
			rabbitTemplate.convertAndSend("pay.exchange", "refund.touser.test", BaseResultTools.JsonObjectToStr(refundmap));
			Map<String, Object> pay2usermap = new HashMap<String, Object>();
			pay2usermap.put("openid", entry.getKey());
			pay2usermap.put("partner_trade_no", String.valueOf(System.currentTimeMillis()));
			rabbitTemplate.convertAndSend("pay.exchange", "pay.touser.test", BaseResultTools.JsonObjectToStr(pay2usermap));
			//threadPool.RefundThread((String)entry.getValue());
			//threadPool.PayToUserThread(entry.getKey(), String.valueOf(System.currentTimeMillis()));
		}
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("success_msg", "清分成功");
		return resmap;
	}

}
