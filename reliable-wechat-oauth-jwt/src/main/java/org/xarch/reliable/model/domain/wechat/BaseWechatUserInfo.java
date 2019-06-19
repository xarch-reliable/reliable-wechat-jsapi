package org.xarch.reliable.model.domain.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseWechatUserInfo {

	@JsonProperty("errcode")
	private String errcode;
	
	@JsonProperty("errmsg")
	private String errmsg;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
