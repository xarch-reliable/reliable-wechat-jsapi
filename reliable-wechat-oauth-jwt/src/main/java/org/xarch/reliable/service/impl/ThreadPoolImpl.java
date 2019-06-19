package org.xarch.reliable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.wechat.WechatUserInfo;
import org.xarch.reliable.model.repository.WXUserInfoRepository;

@Component
public class ThreadPoolImpl {

	@Autowired
	private WXUserInfoRepository wxUserInfoRepository;

	@Async("asyncExecutor")
	public void StorageTokenThread(WechatUserInfo weixinUserInfo) {
		WechatUserInfo existing = wxUserInfoRepository.findByOpenid(weixinUserInfo.getOpenid());
		if (existing != null) {
			return;
		}
		wxUserInfoRepository.save(weixinUserInfo);
	}

}
