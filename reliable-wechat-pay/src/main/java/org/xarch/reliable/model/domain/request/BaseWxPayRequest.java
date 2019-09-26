package org.xarch.reliable.model.domain.request;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信支付请求对象共用的参数存放类<br/>
 * 
 * @author Wei
 *
 */
@MappedSuperclass
public abstract class BaseWxPayRequest {

	/**
	 * <pre>
	 * 字段名：公众账号ID.
	 * 变量名：appid
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：wxd678efh567hg6787
	 * 描述：微信分配的公众账号ID（企业号corpid即为此appId）
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "appid")
	@JacksonXmlCData(value = true)
	protected String appid;
	/**
	 * <pre>
	 * 字段名：商户号.
	 * 变量名：mch_id
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1230000109
	 * 描述：微信支付分配的商户号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "mch_id")
	@JacksonXmlCData(value = true)
	protected String mchId;
	/**
	 * <pre>
	 * 字段名：随机字符串.
	 * 变量名：nonce_str
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 * 描述：随机字符串，不长于32位。推荐随机数生成算法
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "nonce_str")
	@JacksonXmlCData(value = true)
	protected String nonceStr;
	/**
	 * <pre>
	 * 字段名：签名.
	 * 变量名：sign
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：C380BEC2BFD727A4B6845133519F3AD6
	 * 描述：签名，详见签名生成算法
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "sign")
	@JacksonXmlCData(value = true)
	protected String sign;
	
	/**
	 * <pre>
	 * 签名类型.
	 * sign_type
	 * 否
	 * String(32)
	 * HMAC-SHA256
	 * 签名类型，目前支持HMAC-SHA256和MD5
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "sign_type")
	@JacksonXmlCData(value = true)
	protected String signType;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}
