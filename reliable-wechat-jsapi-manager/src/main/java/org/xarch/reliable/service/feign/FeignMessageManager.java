package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xarch.reliable.controller.hystrix.FeignMessageManagerHystrix;

@FeignClient(name = "reliable-wechat-message-manager",fallback = FeignMessageManagerHystrix.class)
public interface FeignMessageManager {

	@GetMapping("/message/send")
	public Map<String, Object> sendCustomerMsg(@RequestBody Map<String, Object> requestdata);
	
}
