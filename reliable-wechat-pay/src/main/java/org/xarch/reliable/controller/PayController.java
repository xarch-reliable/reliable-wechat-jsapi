package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.WxPayService;
import org.xarch.reliable.utils.BaseResultTools;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wechat")
public class PayController {

	@Autowired
	private WxPayService wxPayService;

	@RequestMapping("/pay/h5")
	public Mono<Map<String, Object>> PayMpOrder(@RequestBody Map<String, Object> requestdata) {
		String body = (String)requestdata.get("body");
		String detail = (String)requestdata.get("detail");
		String attach = (String)requestdata.get("attach");
		String totalFee = (String)requestdata.get("total_fee");
		String outTradeNo = (String)requestdata.get("out_trade_no");
		String openid = (String)requestdata.get("openid");
		String ip = "127.0.0.1";
		return wxPayService.testCreateOrder_jsapi(body, outTradeNo, totalFee, ip, openid, detail, attach)
				.flatMap(res -> {
					return Mono.just(BaseResultTools.ObjectToMap(res));
				});
	}
/*
	@RequestMapping("/pay/refund")
	public Mono<Map<String, Object>> PayRefund(@RequestParam(value = "payid", required = true) String payid) {
		String outRefundNo = String.valueOf(System.currentTimeMillis())+payid;
		String totalFee = "111";
		String refundFee = "222";
		return wxPayService.refund(outRefundNo, payid, totalFee, refundFee).flatMap(res -> {
			return Mono.just(BaseResultTools.ObjectToMap(res));
		});
	}
	
	@RequestMapping("/pay/touser")
	public Mono<Map<String, Object>> PayToUser(@RequestParam(value = "openid", required = true) String openid, 
			@RequestParam(value = "partner_trade_no", required = true) String partnerTradeNo) {
		String partnerTradeNo1 = String.valueOf(System.currentTimeMillis());
		String checkName = "NO_CHECK";
		String reUserName = "靠谱达人";
		String amount = "30";
		String desc = "靠谱金";
		String spbillCreateIp = "127.0.0.1";
		return wxPayService.prePayToUser(openid, partnerTradeNo1, checkName, reUserName, amount, desc, spbillCreateIp)
				.flatMap(res -> {
					return Mono.just(BaseResultTools.ObjectToMap(res));
				});
	}
*/
}
