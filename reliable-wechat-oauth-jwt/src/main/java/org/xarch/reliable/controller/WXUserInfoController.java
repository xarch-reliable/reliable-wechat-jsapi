package org.xarch.reliable.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.WXInfoServer;
import reactor.core.publisher.Mono;

@RestController
public class WXUserInfoController {

	@Autowired
	private WXInfoServer wxInfoServer;

	@RequestMapping("/jwt/info")
	public Mono<Map<String, Object>> getJwtInfo(@RequestParam(value = "code", required = true) String code) {
		return wxInfoServer.getToken(code);
	}
	
	@RequestMapping("/jwt/getinfo/all")
	public Mono<List<Map<String, Object>>> getAllUserInfo(){
		return wxInfoServer.getAllUserInfo();
	}

}
