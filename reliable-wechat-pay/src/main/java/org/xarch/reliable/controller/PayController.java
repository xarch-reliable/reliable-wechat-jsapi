package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.WxPayService;
import org.xarch.reliable.utils.transform.BaseResultTools;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wechat")
public class PayController {

	@Autowired
	private WxPayService wxPayService;

	@RequestMapping("/pay/h5")
	public Mono<Map<String, Object>> PayMpOrder(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "payid", required = true) String payid) {
		String body = "测试";
		String detail = "保证红包";
		String attach = "原来靠谱";
		String totalFee = "1";
		String outTradeNo = payid;
		String ip = "127.0.0.1";
		return wxPayService.testCreateOrder_jsapi(body, outTradeNo, totalFee, ip, openid, detail, attach)
				.flatMap(res -> {
					return Mono.just(BaseResultTools.ObjectToMap(res));
				});
	}

	@RequestMapping("/pay/refund")
	public Mono<Map<String, Object>> PayRefund(@RequestParam(value = "payid", required = true) String payid) {
		String outRefundNo = String.valueOf(System.currentTimeMillis());
		return wxPayService.refund(outRefundNo, payid).flatMap(res -> {
			return Mono.just(BaseResultTools.ObjectToMap(res));
		});
	}
}
