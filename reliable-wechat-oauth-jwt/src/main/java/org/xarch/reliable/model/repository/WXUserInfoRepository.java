package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.wxuser.WeixinUserInfo;

public interface WXUserInfoRepository extends JpaRepository<WeixinUserInfo, Long> {

	WeixinUserInfo findByOpenid(String openid);

}
