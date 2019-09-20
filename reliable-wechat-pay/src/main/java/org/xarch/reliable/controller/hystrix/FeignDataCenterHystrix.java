package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.xarch.reliable.service.feign.FeignDataManager;

@Service
public class FeignDataCenterHystrix implements FeignDataManager{

	@Override
	public Map<String, Object> doSupport2DataCenter(@RequestBody Map<String, Object> requestdata) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignDataManager]发起数据请求失败");
		return map;
	}

}
