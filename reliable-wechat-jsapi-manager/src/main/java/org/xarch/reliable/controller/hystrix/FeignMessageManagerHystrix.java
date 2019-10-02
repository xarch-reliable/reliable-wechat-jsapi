package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignMessageManager;

@Service
public class FeignMessageManagerHystrix implements FeignMessageManager {

	@Override
	public Map<String, Object> sendCustomerMsg(Map<String, Object> requestdata) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[sendCustomerMsg]发起数据请求失败");
		return map;
	}

}
