package org.xarch.reliable.config.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
@RefreshScope // bus动态刷新
public class WxConfig {

	/**
	 * 公众号appid.
	 */
	@Value("${wechat.appid:error}")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
