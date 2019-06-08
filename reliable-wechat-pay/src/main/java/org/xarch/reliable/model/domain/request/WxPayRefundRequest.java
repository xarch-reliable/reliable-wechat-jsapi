package org.xarch.reliable.model.domain.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信支付-申请退款请求参数
 * 
 * @author Wei
 *
 */
@JacksonXmlRootElement(localName = "xml")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
//屏蔽重复输出字段[get方法][针对jackson序列化]
@JsonInclude(Include.NON_NULL)
//屏蔽null字段[针对jackson序列化]
@Entity
public class WxPayRefundRequest extends BaseWxPayRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * <pre>
	 * 字段名：设备号.
	 * 变量名：device_info
	 * 是否必填：否
	 * 类型：String(32)
	 * 示例值：13467007045764
	 * 描述：终端设备号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "device_info")
	@JacksonXmlCData(value = true)
	private String deviceInfo;
	/**
	 * <pre>
	 * 字段名：微信订单号.
	 * 变量名：transaction_id
	 * 是否必填：跟out_trade_no二选一
	 * 类型：String(28)
	 * 示例值：1217752501201400000000000000
	 * 描述：微信生成的订单号，在支付通知中有返回
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "transaction_id")
	@JacksonXmlCData(value = true)
	private String transactionId;
	/**
	 * <pre>
	 * 字段名：商户订单号.
	 * 变量名：out_trade_no
	 * 是否必填：跟transaction_id二选一
	 * 类型：String(32)
	 * 示例值：1217752501201400000000000000
	 * 描述：商户侧传给微信的订单号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "out_trade_no")
	@JacksonXmlCData(value = true)
	private String outTradeNo;
	/**
	 * <pre>
	 * 字段名：商户退款单号.
	 * 变量名：out_refund_no
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1217752501201400000000000000
	 * 描述：商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "out_refund_no")
	@JacksonXmlCData(value = true)
	private String outRefundNo;
	/**
	 * <pre>
	 * 字段名：订单金额.
	 * 变量名：total_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：订单总金额，单位为分，只能为整数，详见支付金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "total_fee")
	@JacksonXmlCData(value = true)
	private String totalFee;
	/**
	 * <pre>
	 * 字段名：退款金额.
	 * 变量名：refund_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_fee")
	@JacksonXmlCData(value = true)
	private String refundFee;
	/**
	 * <pre>
	 * 字段名：货币种类.
	 * 变量名：refund_fee_type
	 * 是否必填：否
	 * 类型：String(8)
	 * 示例值：CNY
	 * 描述：货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_fee_type")
	@JacksonXmlCData(value = true)
	private String refundFeeType;
	/**
	 * <pre>
	 * 字段名：操作员.
	 * 变量名：op_user_id
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1900000109
	 * 描述：操作员帐号, 默认为商户号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "op_user_id")
	@JacksonXmlCData(value = true)
	private String opUserId;
	/**
	 * <pre>
	 * 字段名：退款资金来源.
	 * 变量名：refund_account
	 * 是否必填：否
	 * 类型：String(30)
	 * 示例值：REFUND_SOURCE_RECHARGE_FUNDS
	 * 描述：仅针对老资金流商户使用，
	 * <li>REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款），
	 * <li>REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_account")
	@JacksonXmlCData(value = true)
	private String refundAccount;

	/**
	 * <pre>
	 * 字段名：退款原因.
	 * 变量名：refund_account
	 * 是否必填：否
	 * 类型：String(80)
	 * 示例值：商品已售完
	 * 描述：若商户传入，会在下发给用户的退款消息中体现退款原因
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_desc")
	@JacksonXmlCData(value = true)
	private String refundDesc;

	/**
	 * <pre>
	 * 字段名：退款结果通知url.
	 * 变量名：notify_url
	 * 是否必填：否
	 * 类型：String(256)
	 * 示例值：https://weixin.qq.com/notify/
	 * 描述：	异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
	 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "notify_url")
	@JacksonXmlCData(value = true)
	private String notifyUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundFeeType() {
		return refundFeeType;
	}

	public void setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
