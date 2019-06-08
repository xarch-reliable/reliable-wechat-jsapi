package org.xarch.reliable.model.domain.result.h5;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * 微信公众号支付进行统一下单后组装所需参数的类
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
 * </pre>
 * 
 * @author Wei
 *
 */
public class WxPayMpOrderResult {

	@JsonProperty("appId")
	private String appId;
	@JsonProperty("timeStamp")
	private String timeStamp;
	@JsonProperty("nonceStr")
	private String nonceStr;
	/**
	 * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
	 */
	@JsonProperty("package")
	private String packageValue;
	@JsonProperty("signType")
	private String signType;
	@JsonProperty("paySign")
	private String paySign;
	
	@JsonProperty("signature")
	private String signature;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
