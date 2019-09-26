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
public class WechatPayToUserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * SUCCESS/FAIL<br/>
	 * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	@JacksonXmlProperty(localName = "return_code")
	@JacksonXmlCData(value = true)
	protected String returnCode;

	/**
	 * 当return_code为FAIL时返回信息为错误原因
	 */
	@JacksonXmlProperty(localName = "return_msg")
	@JacksonXmlCData(value = true)
	protected String returnMsg;

	/**
	 * SUCCESS/FAIL
	 */
	@JacksonXmlProperty(localName = "result_code")
	@JacksonXmlCData(value = true)
	protected String resultCode;

	/**
	 * 当result_code为FAIL时返回错误代码
	 */
	@JacksonXmlProperty(localName = "err_code")
	@JacksonXmlCData(value = true)
	protected String errCode;

	/**
	 * 当result_code为FAIL时返回错误描述
	 */
	@JacksonXmlProperty(localName = "err_code_des")
	@JacksonXmlCData(value = true)
	protected String errCodeDes;

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
	private String deviceInfo;

	/**
	 * 随机字符串.
	 */
	@JacksonXmlProperty(localName = "nonce_str")
	@JacksonXmlCData(value = true)
	private String nonceStr;
	
	/**
	 * 商户订单号.
	 */
	@JacksonXmlProperty(localName = "partner_trade_no")
	@JacksonXmlCData(value = true)
	private String partnerTradeNo;

	
	/**
	 *微信付款单号.
	 */
	@JacksonXmlProperty(localName = "payment_no")
	@JacksonXmlCData(value = true)
	private String paymentNo;
	
	/**
	 * 付款成功时间.
	 */
	@JacksonXmlProperty(localName = "payment_time")
	@JacksonXmlCData(value = true)
	private String paymentTime;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

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

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
}
