package org.xarch.reliable.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.domain.wechat.BriefUserInfo;
import org.xarch.reliable.model.domain.wechat.WechatUserInfo;
import org.xarch.reliable.service.WechatInfoManager;

import reactor.core.publisher.Mono;

@RestController
public class WechatOauthController {

	private static final Logger logger = LoggerFactory.getLogger(WechatOauthController.class);
	
	@Autowired
	private WechatInfoManager wechatInfoManager;

	@RequestMapping("/jwt/info")
	public Mono<Map<String, Object>> getJwtInfo(@RequestParam(value = "code", required = true) String code) {
		logger.info("WechatOauthController::getJwtInfo() : code = " + code);
		return wechatInfoManager.getJwtUserInfo(code);
	}

	@RequestMapping("/jwt/getinfo/all")
	public Mono<List<Map<String, Object>>> getAllUserInfo() {
		logger.info("WechatOauthController::getAllUserInfo() is called ...");
		return wechatInfoManager.getAllUserInfo();
	}

	@RequestMapping("/jwt/getinfo")
	public Mono<WechatUserInfo> getUserInfoByOpenid(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("WechatOauthController::getUserInfoByOpenid() : openid = " + openid);
		return wechatInfoManager.getUserInfo(openid);
	}
	
	@RequestMapping("/jwt/briefinfo")
	public Mono<BriefUserInfo> getBriefUserInfoByOpenid(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("WechatOauthController::getUserInfoByOpenid() : openid = " + openid);
		return wechatInfoManager.getBriefUserInfo(openid);
	}

}
