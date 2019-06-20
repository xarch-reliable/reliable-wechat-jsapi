package org.xarch.reliable.model.domain.wechat;

import java.io.Serializable;

public class BriefUserInfo extends BaseWechatUserInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nickname;
	private String headimgurl;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
}
