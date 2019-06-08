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
 * 微信支付-申请退款返回结果.
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * </pre>
 * 
 * @author Wei
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
public class WxPayRefundResult extends BaseWxPayResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 微信订单号.
	 */
	@JacksonXmlProperty(localName = "transaction_id")
	@JacksonXmlCData(value = true)
	private String transactionId;

	/**
	 * 商户订单号.
	 */
	@JacksonXmlProperty(localName = "out_trade_no")
	@JacksonXmlCData(value = true)
	private String outTradeNo;

	/**
	 * 商户退款单号.
	 */
	@JacksonXmlProperty(localName = "out_refund_no")
	@JacksonXmlCData(value = true)
	private String outRefundNo;

	/**
	 * 微信退款单号.
	 */
	@JacksonXmlProperty(localName = "refund_id")
	@JacksonXmlCData(value = true)
	private String refundId;

	/**
	 * 退款金额.
	 */
	@JacksonXmlProperty(localName = "refund_fee")
	@JacksonXmlCData(value = true)
	private String refundFee;

	/**
	 * 应结退款金额.
	 */
	@JacksonXmlProperty(localName = "settlement_refund_fee")
	@JacksonXmlCData(value = true)
	private String settlementRefundFee;

	/**
	 * 标价金额.
	 */
	@JacksonXmlProperty(localName = "total_fee")
	@JacksonXmlCData(value = true)
	private String totalFee;

	/**
	 * 应结订单金额.
	 */
	@JacksonXmlProperty(localName = "settlement_total_fee")
	@JacksonXmlCData(value = true)
	private String settlementTotalFee;

	/**
	 * 标价币种.
	 */
	@JacksonXmlProperty(localName = "fee_type")
	@JacksonXmlCData(value = true)
	private String feeType;

	/**
	 * 现金支付金额.
	 */
	@JacksonXmlProperty(localName = "cash_fee")
	@JacksonXmlCData(value = true)
	private String cashFee;

	/**
	 * 现金支付币种.
	 */
	@JacksonXmlProperty(localName = "cash_fee_type")
	@JacksonXmlCData(value = true)
	private String cashFeeType;

	/**
	 * 现金退款金额，单位为分，只能为整数，详见支付金额.
	 */
	@JacksonXmlProperty(localName = "cash_refund_fee")
	@JacksonXmlCData(value = true)
	private String cashRefundFee;

	/**
	 * 退款代金券使用数量.
	 */
	@JacksonXmlProperty(localName = "coupon_refund_count")
	@JacksonXmlCData(value = true)
	private String couponRefundCount;

	/**
	 * <pre>
	 * 字段名：代金券退款总金额.
	 * 变量名：coupon_refund_fee
	 * 是否必填：否
	 * 类型：Int
	 * 示例值：100
	 * 描述：代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "coupon_refund_fee")
	@JacksonXmlCData(value = true)
	private String couponRefundFee;
	
	@JacksonXmlProperty(localName = "refund_channel")
	@JacksonXmlCData(value = true)
	private String refundChannel;
	

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

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public void setSettlementRefundFee(String settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getCashFee() {
		return cashFee;
	}

	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public String getCashRefundFee() {
		return cashRefundFee;
	}

	public void setCashRefundFee(String cashRefundFee) {
		this.cashRefundFee = cashRefundFee;
	}

	public String getCouponRefundCount() {
		return couponRefundCount;
	}

	public void setCouponRefundCount(String couponRefundCount) {
		this.couponRefundCount = couponRefundCount;
	}

	public String getCouponRefundFee() {
		return couponRefundFee;
	}

	public void setCouponRefundFee(String couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}

	public String getRefundChannel() {
		return refundChannel;
	}

	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel;
	}

}
