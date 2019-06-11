package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.xarch.reliable.controller.hystrix.FeignCentralHystrix;

@FeignClient(name = "reliable-wechat-central-controller",fallback = FeignCentralHystrix.class)
public interface FeignCentralManager {
	@GetMapping("/token/get")
	public String getAccessToken();
	
	@GetMapping("/ticket/get")
	public String getTicket();
	
}
