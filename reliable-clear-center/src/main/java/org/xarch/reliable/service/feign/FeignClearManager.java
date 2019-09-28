package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xarch.reliable.controller.hystrix.FeignClearCenterHystrix;

@FeignClient(name = "reliable-clear-center", fallback = FeignClearCenterHystrix.class)
public interface FeignClearManager {

	@GetMapping("/reliable/clear/support")
	public Map<String, Object> doSupport2ClearCenter(@RequestBody Map<String, Object> requestdata);
	
}
