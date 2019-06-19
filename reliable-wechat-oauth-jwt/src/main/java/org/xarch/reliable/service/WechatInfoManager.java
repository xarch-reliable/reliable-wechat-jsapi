package org.xarch.reliable.service;

import java.util.List;
import java.util.Map;

import org.xarch.reliable.model.domain.wechat.WechatUserInfo;

import reactor.core.publisher.Mono;

public interface WechatInfoManager {

	/**
	 * 通过code获取access_token再获取用户信息并加密
	 *
	 * @param code	前端用户主动获取的凭证
	 * @return 授权之后加密的token和简要用户信息
	 */
	public Mono<Map<String, Object>> getJwtUserInfo(String code);

	/**
	 * 获取全部用户列表
	 *
	 * @return 用户List列表
	 */
	public Mono<List<Map<String, Object>>> getAllUserInfo();

	/**
	 * 通过openid获取用户信息
	 *
	 * @param openid	用户的识别码
	 * @return 用户信息
	 */
	public Mono<WechatUserInfo> getUserInfo(String openid);
}
