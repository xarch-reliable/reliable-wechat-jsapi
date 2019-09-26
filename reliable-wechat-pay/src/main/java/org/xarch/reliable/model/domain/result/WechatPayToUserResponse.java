package org.xarch.reliable.model.domain.result;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * <pre>
 * 微信支付-企业付款到零钱
 * https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
 * </pre>
 * 
 * @author wancy
 *
 */
@JacksonXmlRootElement(localName = "xml")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
//屏蔽重复输出字段[get方法][针对jackson序列化]
@JsonInclude(Include.NON_NULL)
//屏蔽null字段[针对jackson序列化]
@JsonIgnoreProperties(ignoreUnknown=true)
//反序列化时要忽略所有pojo中不存在的属性
@Entity
public class WechatPayToUserResponse extends BaseWxPayResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 商户appid.
	 */
	@JacksonXmlProperty(localName = "mch_appid")
	@JacksonXmlCData(value = true)
	private String mchAppid;

	/**
	 * 商户号.
	 */
	@JacksonXmlProperty(localName = "mchid")
	@JacksonXmlCData(value = true)
	private String mchid;

	/**
	 *设备号.
	 */
	@JacksonXmlProperty(localName = "device_info")
	@JacksonXmlCData(value = true)
	private String device_info;

	/**
	 * 随机字符串.
	 */
	@JacksonXmlProperty(localName = "nonce_str")
	@JacksonXmlCData(value = true)
	private String nonce_str;
	
	/**
	 * 商户订单号.
	 */
	@JacksonXmlProperty(localName = "partner_trade_no")
	@JacksonXmlCData(value = true)
	private String partner_trade_no;

	
	/**
	 *微信付款单号.
	 */
	@JacksonXmlProperty(localName = "payment_no")
	@JacksonXmlCData(value = true)
	private String payment_no;
	
	/**
	 * 付款成功时间.
	 */
	@JacksonXmlProperty(localName = "payment_time")
	@JacksonXmlCData(value = true)
	private String payment_time;

	public String getMchAppid() {
		return mchAppid;
	}

	public void setMchAppid(String mchAppid) {
		this.mchAppid = mchAppid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getPartner_trade_no() {
		return partner_trade_no;
	}

	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}

	public String getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}

}
