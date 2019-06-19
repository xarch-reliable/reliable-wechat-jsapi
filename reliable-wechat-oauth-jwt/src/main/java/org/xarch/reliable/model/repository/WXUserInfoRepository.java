package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.wechat.WechatUserInfo;

public interface WXUserInfoRepository extends JpaRepository<WechatUserInfo, Long> {

	WechatUserInfo findByOpenid(String openid);

}
