package org.xarch.reliable.service;

import java.util.Map;

import reactor.core.publisher.Mono;

public interface JoinQrCodeService {

	public Mono<Map<String, Object>> createForeverTicket(String actid, String openid);
	
	public String getQrcode(String ticket, String actid);
}
