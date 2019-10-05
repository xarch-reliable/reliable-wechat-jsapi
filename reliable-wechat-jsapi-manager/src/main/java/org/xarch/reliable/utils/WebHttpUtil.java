package org.xarch.reliable.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.xarch.reliable.model.doman.JoinQrCode;
import org.xarch.reliable.service.feign.FeignCentralManager;
import org.xarch.reliable.utils.BaseResultTools;
import com.alibaba.fastjson.JSONObject;

import reactor.core.publisher.Mono;

@Service
public class WebHttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WebHttpUtil.class);

	private static final String joinQrCodeUrlBase = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	
	@Autowired
	private FeignCentralManager feignCentralManager;
	
	public String getAccessToken() {
		Map<String, Object> resmap = feignCentralManager.getAccessToken();
		return (String)resmap.get("access_token");
	}
	
	public Mono<JoinQrCode> CreateWechatQrCodeWebPost(JSONObject data){
		
		String access_token = getAccessToken();
		logger.info("[postUrl] "+joinQrCodeUrlBase+access_token);
		logger.info("[data] "+data);
		return WebClient.create().post().uri(joinQrCodeUrlBase+access_token)
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.syncBody(data)
			.retrieve().bodyToMono(String.class).flatMap(res ->{
				return Mono.just(BaseResultTools.fromJSON(res, JoinQrCode.class));
			});
	}
}
