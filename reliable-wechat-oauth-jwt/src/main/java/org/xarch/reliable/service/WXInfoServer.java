package org.xarch.reliable.service;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Mono;

public interface WXInfoServer {

	public Mono<Map<String, Object>> getToken(String code);

	public Mono<List<Map<String, Object>>> getAllUserInfo();
}
