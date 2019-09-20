package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xarch.reliable.controller.hystrix.FeignDataCenterHystrix;

@FeignClient(name = "reliable-data-center", fallback = FeignDataCenterHystrix.class)
public interface FeignDataManager {
	
	@GetMapping("/reliable/data/support")
	public Map<String, Object> doSupport2DataCenter(@RequestBody Map<String, Object> requestdata);
	
}

