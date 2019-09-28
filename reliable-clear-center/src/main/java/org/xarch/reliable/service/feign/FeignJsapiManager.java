package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignJsapiHystrix;

@FeignClient(name = "reliable-wechat-jsapi-manager", fallback = FeignJsapiHystrix.class)
public interface FeignJsapiManager {

	@GetMapping("jsapi/share/signature")
	public Map<String, Object> getShareInfo(@RequestParam(value = "url", required = true) String url);
}
