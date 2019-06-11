package org.xarch.reliable.model.doman.jsapi;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsApiParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("appid")
	private String appid;

	@JsonProperty("url")
	private String url;

	@JsonProperty("nonceStr")
	private String nonceStr;

	@JsonProperty("timeStamp")
	private String timeStamp;

	@JsonProperty("signature")
	private String signature;

	@JsonProperty("xraction")
	private String xraction;

	public JsApiParams() {
		super();
	}

	public JsApiParams(String appid, String url, String nonceStr, String timeStamp, String signature, String xraction) {
		super();
		this.appid = appid;
		this.url = url;
		this.nonceStr = nonceStr;
		this.timeStamp = timeStamp;
		this.signature = signature;
		this.xraction = xraction;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getXraction() {
		return xraction;
	}

	public void setXraction(String xraction) {
		this.xraction = xraction;
	}

}
