package org.xarch.reliable.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.weixin.WxConfig;
import org.xarch.reliable.model.domain.wxuser.WeixinUserInfo;
import org.xarch.reliable.model.repository.WXUserInfoRepository;
import org.xarch.reliable.service.WXInfoServer;
import org.xarch.reliable.service.thread.ThreadPool;
import org.xarch.reliable.utils.jwt.JwtUtils;
import org.xarch.reliable.utils.http.WXUserInfoHttpUtils;
import org.xarch.reliable.utils.transform.BaseResultTools;

import reactor.core.publisher.Mono;

@Service
public class WXInfoServerImpl implements WXInfoServer {

	private static final Logger logger = LoggerFactory.getLogger(WXInfoServerImpl.class);

	@Autowired
	private WxConfig wxConfig;

	@Autowired
	private ThreadPool threadPool;

	@Autowired
	private WXUserInfoRepository wxUserInfoRepository;

	@Override
	public Mono<Map<String, Object>> getToken(String code) {
		String appid = wxConfig.getAppId();
		String secret = wxConfig.getAppseCret();
		String keyStr = wxConfig.getJwtKey();
		return WXUserInfoHttpUtils.GetAccessToken(appid, secret, code).flatMap(r -> {
			Map<String, Object> map = new HashMap<>();
			if (StringUtils.isBlank(r.getAccess_token())) {
				map.put("body", BaseResultTools.JsonObjectToMap(r));
				logger.error("HttpServerImpl::GetAccessToken()-->获取accesstoken失败");
				logger.error(r.getErrcode() + "=" + r.getErrmsg());
				return Mono.just(map);
			}
			return WXUserInfoHttpUtils.GetUserInfo(r.getAccess_token(), r.getOpenid()).flatMap(info -> {
				if (StringUtils.isBlank(info.getOpenid())) {
					map.put("body", BaseResultTools.JsonObjectToMap(info));
					logger.error("HttpServerImpl::GetUserInfo()-->获取userinfo失败");
					logger.error(info.getErrcode() + "=" + info.getErrmsg());
					return Mono.just(map);
				}
				logger.error("HttpServerImpl::GetUserInfo() wancy success !!!!!!!!");
				Map<String, Object> claims = new HashMap<>();
				claims.put("openid", info.getOpenid());
				String subject = "Reliable_TOKEN";
				try {
					map.put("token", JwtUtils.createJWT(keyStr, claims, subject));
					map.put("nickname", info.getNickname());
					map.put("sex", info.getSex());
					map.put("province", info.getProvince());
					map.put("city", info.getCity());
					map.put("headimgurl", info.getHeadimgurl());
					threadPool.StorageTokenThread(info);
					return Mono.just(map);
				} catch (Exception e) {
					logger.error("HttpServerImpl::JwtUtils.createJWT()-->创建JWTtoken失败");
					e.printStackTrace();
				}
				map.put("body", "创建JWTtoken失败");
				return Mono.just(map);
			});
		});
	}

	@Override
	public Mono<List<Map<String, Object>>> getAllUserInfo() {
		return Mono.just(wxUserInfoRepository.findAll()).flatMap(r -> {
			List<Map<String, Object>> list = new ArrayList<>();
			for (WeixinUserInfo info : r) {
				Map<String, Object> map = new HashMap<>();
				map.put("openid", info.getOpenid());
				map.put("nickname", info.getNickname());
				list.add(map);
			}
			return Mono.just(list);
		});
	}

}
