package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.service.feign.FeignClearManager;

public class FeignClearCenterHystrix implements FeignClearManager {

	@Override
	public Map<String, Object> doSupport2ClearCenter(Map<String, Object> requestdata) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignClearManager]发起数据请求失败");
		return map;
	}

}
