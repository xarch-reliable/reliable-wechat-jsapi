package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-wechat-pay", fallback = FeignPayHystrix.class)
public interface FeignPayManager {

	@GetMapping("/wechat/pay/h5")
	public Map<String, Object> getPayMpOrder(
			@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "payid", required = true) String payid, 
			@RequestParam(value = "actid", required = true) String actid);

	@GetMapping("/wechat/pay/refund")
	public Map<String, Object> getPayRefund(@RequestParam(value = "payid", required = true) String payid);
	
	@GetMapping("/wechat/pay/touser")
	public Map<String, Object> toPay(@RequestParam(value = "openid", required = true) String openid, 
			@RequestParam(value = "partner_trade_no", required = true) String partnerTradeNo);
}
