package org.xarch.reliable.service;

import java.util.List;
import java.util.Map;

import org.xarch.reliable.model.domain.wxuser.WeixinUserInfo;

import reactor.core.publisher.Mono;

public interface WXInfoServer {

	public Mono<Map<String, Object>> getToken(String code);

	public Mono<List<Map<String, Object>>> getAllUserInfo();
	
	public Mono<WeixinUserInfo> getUserInfo(String openid);
}
