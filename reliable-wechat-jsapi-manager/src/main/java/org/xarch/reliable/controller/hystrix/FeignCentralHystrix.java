package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignCentralManager;


@Service
public class FeignCentralHystrix implements FeignCentralManager {

	@Override
	public Map<String, Object> getAccessToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignCentralHystrix]发起数据请求失败");
		return map;
	}

	@Override
	public String getTicket() {
		return "getTicket->error";
	}

	
}
