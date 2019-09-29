package org.xarch.reliable.service;

import org.xarch.reliable.config.RabbitConfig;
import org.xarch.reliable.utils.BaseResultTools;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PayMsgReceiver {
	
	@Autowired
	private WxPayService wxPayService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	@RabbitListener(queues = RabbitConfig.refundQueue)
	public void RefundReceive(String datastr) {
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		String outRefundNo = (String)((Map<String, Object>)data).get("out_refund_no");
		String outTradeNo = (String)((Map<String, Object>)data).get("out_trade_no");
		wxPayService.refund(outRefundNo, outTradeNo);
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RabbitHandler
	@RabbitListener(queues = RabbitConfig.payToUserQueue)
	public void PayToUserReceive(String datastr) {
		Map data = BaseResultTools.fromJSON(datastr, Map.class);
		String openid = (String)((Map<String, Object>)data).get("openid");
		String partnerTradeNo = (String)((Map<String, Object>)data).get("partner_trade_no");
		String checkName = "NO_CHECK";
		String reUserName = "靠谱达人";
		String amount = "30";
		String desc = "靠谱金";
		String spbillCreateIp = "127.0.0.1";
		wxPayService.prePayToUser(openid, partnerTradeNo, checkName, reUserName, amount, desc, spbillCreateIp);
		return;
	}
	
}
