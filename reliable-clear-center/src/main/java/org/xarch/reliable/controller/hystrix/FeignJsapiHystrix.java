package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignJsapiManager;

@Service
public class FeignJsapiHystrix implements FeignJsapiManager {

	@Override
	public Map<String, Object> getShareInfo(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignJsapiManager]获取getShareInfo失败");
		map.put("url", url);
		return map;
	}

}
