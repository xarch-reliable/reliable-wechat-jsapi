package org.xarch.reliable.config.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
@RefreshScope // bus动态刷新
public class WxPayConfig {

	/**
	 * 公众号appid.
	 */
	@Value("${wechat.appid:error}")
	private String appId;

	/**
	 * 商户号.
	 */
	@Value("${wechat.pay.mch.id:error}")
	private String mchId;
	/**
	 * 商户密钥.
	 */
	@Value("${wechat.pay.mch.key:error}")
	private String mchKey;

	/**
	 * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数.
	 */
	@Value("${wechat.pay.notify.unified:error}")
	private String unifiedUrl;

	/**
	 * 退款
	 */
	@Value("${wechat.pay.notify.refund:error}")
	private String refundUrl;

	/**
	 * 交易类型.
	 * 
	 * <pre>
	 * JSAPI--公众号支付
	 * NATIVE--原生扫码支付
	 * APP--app支付
	 * </pre>
	 */
	@Value("${wechat.pay.tradetype:error}")
	private String tradeType;

	/**
	 * 签名方式. 有两种HMAC_SHA256 和MD5
	 *
	 */
	@Value("${wechat.pay.signtype:error}")
	private String signType;

	/**
	 * p12证书文件的绝对路径或者以classpath:开头的类路径.
	 */
	@Value("${wechat.pay.filepath:error}")
	private String filePath;

	public String getAppId() {
		return appId;
	}

	public String getMchId() {
		return mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public String getUnifiedUrl() {
		return unifiedUrl;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public String getSignType() {
		return signType;
	}

	public String getFilePath() {
		return filePath;
	}

}
