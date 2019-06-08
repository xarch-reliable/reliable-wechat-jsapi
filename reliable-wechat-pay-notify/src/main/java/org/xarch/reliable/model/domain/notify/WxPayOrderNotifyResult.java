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

@JacksonXmlRootElement(localName = "xml")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
//屏蔽重复输出字段[get方法][针对jackson序列化]
@JsonInclude(Include.NON_NULL)
//屏蔽null字段[针对jackson序列化]
@Entity
public class WxPayOrderNotifyResult extends BaseWxPayResult implements Serializable {

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
	 * 示例值：013467007045764
	 * 描述：微信支付分配的终端设备号，
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "device_info")
	@JacksonXmlCData(value = true)
	private String deviceInfo;

	/**
	 * <pre>
	 * 字段名：用户标识.
	 * 变量名：openid
	 * 是否必填：是
	 * 类型：String(128)
	 * 示例值：wxd930ea5d5a258f4f
	 * 描述：用户在商户appid下的唯一标识
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "openid")
	@JacksonXmlCData(value = true)
	private String openid;

	/**
	 * <pre>
	 * 字段名：是否关注公众账号.
	 * 变量名：is_subscribe
	 * 是否必填：否
	 * 类型：String(1)
	 * 示例值：Y
	 * 描述：用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "is_subscribe")
	@JacksonXmlCData(value = true)
	private String isSubscribe;

	/**
	 * <pre>
	 * 字段名：交易类型.
	 * 变量名：trade_type
	 * 是否必填：是
	 * 类型：String(16)
	 * 示例值：JSAPI
	 * JSA描述：PI、NATIVE、APP
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "trade_type")
	@JacksonXmlCData(value = true)
	private String tradeType;

	/**
	 * <pre>
	 * 字段名：付款银行.
	 * 变量名：bank_type
	 * 是否必填：是
	 * 类型：String(16)
	 * 示例值：CMC
	 * 描述：银行类型，采用字符串类型的银行标识，银行类型见银行列表
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "bank_type")
	@JacksonXmlCData(value = true)
	private String bankType;

	/**
	 * <pre>
	 * 字段名：订单金额.
	 * 变量名：total_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：订单总金额，单位为分
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "total_fee")
	@JacksonXmlCData(value = true)
	private Integer totalFee;
	/**
	 * <pre>
	 * 字段名：应结订单金额.
	 * 变量名：settlement_total_fee
	 * 是否必填：否
	 * 类型：Int
	 * 示例值：100
	 * 描述：应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "settlement_total_fee")
	@JacksonXmlCData(value = true)
	private Integer settlementTotalFee;
	/**
	 * <pre>
	 * 字段名：货币种类.
	 * 变量名：fee_type
	 * 是否必填：否
	 * 类型：String(8)
	 * 示例值：CNY
	 * 描述：货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "fee_type")
	@JacksonXmlCData(value = true)
	private String feeType;
	/**
	 * <pre>
	 * 字段名：现金支付金额.
	 * 变量名：cash_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值：100
	 * 描述：现金支付金额订单现金支付金额，详见支付金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "cash_fee")
	@JacksonXmlCData(value = true)
	private Integer cashFee;
	/**
	 * <pre>
	 * 字段名：现金支付货币类型.
	 * 变量名：cash_fee_type
	 * 是否必填：否
	 * 类型：String(16)
	 * 示例值：CNY
	 * 描述：货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "cash_fee_type")
	@JacksonXmlCData(value = true)
	private String cashFeeType;
	/**
	 * <pre>
	 * 字段名：总代金券金额.
	 * 变量名：coupon_fee
	 * 是否必填：否
	 * 类型：Int
	 * 示例值：10
	 * 描述：代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "coupon_fee")
	@JacksonXmlCData(value = true)
	private Integer couponFee;

	/**
	 * <pre>
	 * 字段名：代金券使用数量.
	 * 变量名：coupon_count
	 * 是否必填：否
	 * 类型：Int
	 * 示例值：1
	 * 描述：代金券使用数量
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "coupon_count")
	@JacksonXmlCData(value = true)
	private Integer couponCount;

	/**
	 * <pre>
	 * 字段名：微信支付订单号.
	 * 变量名：transaction_id
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：1217752501201407033233368018
	 * 描述：微信支付订单号
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
	 * 示例值：1212321211201407033568112322
	 * 描述：商户系统的订单号，与请求一致。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "out_trade_no")
	@JacksonXmlCData(value = true)
	private String outTradeNo;
	/**
	 * <pre>
	 * 字段名：商家数据包.
	 * 变量名：attach
	 * 是否必填：否
	 * 类型：String(128)
	 * 示例值：123456
	 * 描述：商家数据包，原样返回
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "attach")
	@JacksonXmlCData(value = true)
	private String attach;

	/**
	 * <pre>
	 * 字段名：支付完成时间.
	 * 变量名：time_end
	 * 是否必填：是
	 * 类型：String(14)
	 * 示例值：20141030133525
	 * 描述：支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "time_end")
	@JacksonXmlCData(value = true)
	private String timeEnd;

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
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

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
