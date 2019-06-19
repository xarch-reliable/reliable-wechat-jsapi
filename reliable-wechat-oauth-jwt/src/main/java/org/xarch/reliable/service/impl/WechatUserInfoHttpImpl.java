package org.xarch.reliable.service.impl;

import org.springframework.web.reactive.function.client.WebClient;
import org.xarch.reliable.model.domain.wechat.AccessToken;
import org.xarch.reliable.model.domain.wechat.WechatUserInfo;
import org.xarch.reliable.utils.BaseResultUtils;

import reactor.core.publisher.Mono;

public class WechatUserInfoHttpImpl {

	private static final String OAUTH_BASE_URL = "https://api.weixin.qq.com";
	private static final String OAUTH_ACCESS_TOKEN_PATH = "/sns/oauth2/access_token";
	private static final String OAUTH_USER_INFO_PATH = "/sns/userinfo";

	public static Mono<AccessToken> GetAccessToken(String appid, String secret, String code) {
		WebClient webClient = WebClient.builder().baseUrl(OAUTH_BASE_URL).build();
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(OAUTH_ACCESS_TOKEN_PATH).queryParam("appid", appid)
						.queryParam("secret", secret).queryParam("code", code)
						.queryParam("grant_type", "authorization_code").build())
				.retrieve().bodyToMono(String.class).flatMap(res -> {
					return Mono.just(BaseResultUtils.fromJSON(res, AccessToken.class));
				});
	}

	public static Mono<WechatUserInfo> GetUserInfo(String access_token, String openid) {
		WebClient webClient = WebClient.builder().baseUrl(OAUTH_BASE_URL).build();
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(OAUTH_USER_INFO_PATH).queryParam("access_token", access_token)
						.queryParam("openid", openid).queryParam("lang", "zh_CN").build())
				.retrieve().bodyToMono(String.class).flatMap(res -> {
					return Mono.just(BaseResultUtils.fromJSON(res, WechatUserInfo.class));
				});
	}
}
