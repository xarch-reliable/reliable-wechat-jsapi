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

public class PayMsgReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(PayMsgReceiver.class);
	
	@Autowired
	private WxPayService wxPayService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	@RabbitListener(queues = RabbitConfig.refundQueue)
	public void RefundReceive(String datastr) {
		logger.info("RefundReceive 接收消息 : " +datastr);
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		String outRefundNo = (String)((Map<String, Object>)data).get("out_refund_no");
		String outTradeNo = (String)((Map<String, Object>)data).get("out_trade_no");
		String totalFee = (String)((Map<String, Object>)data).get("total_fee");
		String refundFee = (String)((Map<String, Object>)data).get("refund_fee");
		wxPayService.refund(outRefundNo, outTradeNo, totalFee, refundFee);
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	@RabbitListener(queues = RabbitConfig.payToUserQueue)
	public void PayToUserReceive(String datastr) {
		logger.info("PayToUserReceive 接收消息 : " +datastr);
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		String openid 			= (String)((Map<String, Object>)data).get("openid");
		String partnerTradeNo 	= (String)((Map<String, Object>)data).get("partner_trade_no");
		String checkName 		= (String)((Map<String, Object>)data).get("check_name");
		String reUserName 		= (String)((Map<String, Object>)data).get("re_user_name");
		String amount 			= (String)((Map<String, Object>)data).get("amount");
		String desc 			= (String)((Map<String, Object>)data).get("desc");
		String spbillCreateIp 	= "127.0.0.1";
		wxPayService.prePayToUser(openid, partnerTradeNo, checkName, reUserName, amount, desc, spbillCreateIp);
		return;
	}
	
}
