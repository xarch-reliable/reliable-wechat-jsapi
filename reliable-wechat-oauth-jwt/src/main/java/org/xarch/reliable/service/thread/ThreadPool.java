package org.xarch.reliable.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.wxuser.WeixinUserInfo;
import org.xarch.reliable.model.repository.WXUserInfoRepository;

@Component
public class ThreadPool {

	@Autowired
	private WXUserInfoRepository wxUserInfoRepository;

	@Async("asyncExecutor")
	public void StorageTokenThread(WeixinUserInfo weixinUserInfo) {
		WeixinUserInfo existing = wxUserInfoRepository.findByOpenid(weixinUserInfo.getOpenid());
		if (existing != null) {
			return;
		}
		wxUserInfoRepository.save(weixinUserInfo);
	}

}
