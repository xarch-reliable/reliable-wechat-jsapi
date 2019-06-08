package org.xarch.reliable.model.domain.notify;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.xarch.reliable.model.domain.result.BaseWxPayResult;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "root")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
//屏蔽重复输出字段[get方法][针对jackson序列化]
@JsonInclude(Include.NON_NULL)
//屏蔽null字段[针对jackson序列化]
@Entity
public class WxPayRefundNotifyReqInfo extends BaseWxPayResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * <pre>
	 * 字段名：微信订单号.
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1217752501201407033233368018
	 * 描述：微信订单号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "transaction_id")
	@JacksonXmlCData(value = true)
	private String transactionId;

	/**
	 * <pre>
	 * 字段名：商户订单号.
	 * 变量名：out_trade_no
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1217752501201407033233368018
	 * 描述：商户系统内部的订单号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "out_trade_no")
	@JacksonXmlCData(value = true)
	private String outTradeNo;

	/**
	 * <pre>
	 * 字段名：微信退款单号.
	 * 变量名：refund_id
	 * 是否必填：是
	 * 类型：String(28)
	 * 示例值：1217752501201407033233368018
	 * 描述：微信退款单号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_id")
	@JacksonXmlCData(value = true)
	private String refundId;

	/**
	 * <pre>
	 * 字段名：商户退款单号.
	 * 变量名：out_refund_no
	 * 是否必填：是
	 * 类型：String(64)
	 * 示例值：1217752501201407033233368018
	 * 描述：商户退款单号
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
	private Integer totalFee;

	/**
	 * <pre>
	 * 字段名：结订单金额.
	 * 变量名：settlement_total_fee
	 * 是否必填：否
	 * 类型：Int
	 * 示例值：100
	 * 描述：当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "settlement_total_fee")
	@JacksonXmlCData(value = true)
	private Integer settlementTotalFee;

	/**
	 * <pre>
	 * 字段名：申请退款金额.
	 * 变量名：refund_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：退款总金额,单位为分
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_fee")
	@JacksonXmlCData(value = true)
	private Integer refundFee;

	/**
	 * <pre>
	 * 字段名：退款金额.
	 * 变量名：settlement_refund_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "settlement_refund_fee")
	@JacksonXmlCData(value = true)
	private Integer settlementRefundFee;

	/**
	 * <pre>
	 * 字段名：退款状态.
	 * 变量名：refund_status
	 * 是否必填：是
	 * 类型：String(16)
	 * 示例值：SUCCESS
	 * 描述：SUCCESS-退款成功，CHANGE-退款异常，REFUNDCLOSE—退款关闭
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_status")
	@JacksonXmlCData(value = true)
	private String refundStatus;

	/**
	 * <pre>
	 * 字段名：退款成功时间.
	 * 变量名：success_time
	 * 是否必填：否
	 * 类型： String(20)
	 * 示例值：2017-12-15 09:46:01
	 * 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "success_time")
	@JacksonXmlCData(value = true)
	private String successTime;

	/**
	 * <pre>
	 * 字段名：退款入账账户.
	 * 变量名：refund_recv_accout
	 * 是否必填：是
	 * 类型：String(64)
	 * 示例值：招商银行信用卡0403
	 * 描述：取当前退款单的退款入账方，1）退回银行卡：{银行名称}{卡类型}{卡尾号}，2）退回支付用户零钱:支付用户零钱 ，3）退还商户: 商户基本账户，商户结算银行账户，4）退回支付用户零钱通: 支付用户零钱通
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_recv_accout")
	@JacksonXmlCData(value = true)
	private String refundRecvAccout;

	/**
	 * <pre>
	 * 字段名：退款资金来源.
	 * 变量名：refund_account
	 * 是否必填：是
	 * 类型：String(30)
	 * 示例值：REFUND_SOURCE_RECHARGE_FUNDS
	 * 描述：REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户，REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_account")
	@JacksonXmlCData(value = true)
	private String refundAccount;

	/**
	 * <pre>
	 * 字段名：退款发起来源.
	 * 变量名：refund_request_source
	 * 是否必填：是
	 * 类型：String(30)
	 * 示例值：API
	 * 描述：API接口，VENDOR_PLATFORM商户平台
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "refund_request_source")
	@JacksonXmlCData(value = true)
	private String refundRequestSource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public Integer getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}

	public Integer getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public void setSettlementRefundFee(Integer settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}

	public void setRefundRecvAccout(String refundRecvAccout) {
		this.refundRecvAccout = refundRecvAccout;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	public String getRefundRequestSource() {
		return refundRequestSource;
	}

	public void setRefundRequestSource(String refundRequestSource) {
		this.refundRequestSource = refundRequestSource;
	}

}
