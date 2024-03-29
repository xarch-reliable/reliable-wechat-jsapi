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
 * 统一下单API[普通商户] <br/>
 * 统一下单请求参数对象.
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
public class WxPayUnifiedOrderRequest extends BaseWxPayRequest implements Serializable{

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
	 * 描述：终端设备号(门店号或收银设备Id)，注意：PC网页或公众号内支付请传"WEB"
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "device_info")
	@JacksonXmlCData(value = true)
	private String deviceInfo;
	/**
	 * <pre>
	 * 字段名：商品描述.
	 * 变量名：body
	 * 是否必填：是
	 * 类型：String(128)
	 * 示例值： 腾讯充值中心-QQ会员充值
	 * 描述：商品简单描述，该字段须严格按照规范传递，具体请见参数规定
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "body")
	private String body;
	/**
	 * <pre>
	 * 字段名：商品详情.
	 * 变量名：detail
	 * 是否必填：否
	 * 类型：String(6000)
	 * 示例值： {  "goods_detail":[
	 *  {
	 * "goods_id":"iphone6s_16G",
	 * "wxpay_goods_id":"1001",
	 * "goods_name":"iPhone6s 16G",
	 * "goods_num":1,
	 * "price":528800,
	 * "goods_category":"123456",
	 * "body":"苹果手机"
	 * },
	 * {
	 * "goods_id":"iphone6s_32G",
	 * "wxpay_goods_id":"1002",
	 * "goods_name":"iPhone6s 32G",
	 * "quantity":1,
	 * "price":608800,
	 * "goods_category":"123789",
	 * "body":"苹果手机"
	 * }
	 * ]
	 * }
	 * 描述：商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
	 * goods_detail []：
	 * └ goods_id String 必填 32 商品的编号
	 * └ wxpay_goods_id String 可选 32 微信支付定义的统一商品编号
	 * └ goods_name String 必填 256 商品名称
	 * └ goods_num Int 必填 商品数量
	 * └ price Int 必填 商品单价，单位为分
	 * └ goods_category String 可选 32 商品类目Id
	 * └ body String 可选 1000 商品描述信息
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "detail")
	@JacksonXmlCData(value = true)
	private String detail;
	/**
	 * <pre>
	 * 字段名：附加数据.
	 * 变量名：attach
	 * 是否必填：否
	 * 类型：String(127)
	 * 示例值： 深圳分店
	 * 描述：  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "attach")
	@JacksonXmlCData(value = true)
	private String attach;
	/**
	 * <pre>
	 * 字段名：商户订单号.
	 * 变量名：out_trade_no
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：20150806125346
	 * 描述：商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "out_trade_no")
	@JacksonXmlCData(value = true)
	private String outTradeNo;
	/**
	 * <pre>
	 * 字段名：货币类型.
	 * 变量名：fee_type
	 * 是否必填：否
	 * 类型：String(16)
	 * 示例值：CNY
	 * 描述： 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "fee_type")
	@JacksonXmlCData(value = true)
	private String feeType;
	/**
	 * <pre>
	 * 字段名：总金额.
	 * 变量名：total_fee
	 * 是否必填：是
	 * 类型：Int
	 * 示例值： 888
	 * 描述：订单总金额，单位为分，详见支付金额
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "total_fee")
	@JacksonXmlCData(value = true)
	private String totalFee;
	/**
	 * <pre>
	 * 字段名：终端IP.
	 * 变量名：spbill_create_ip
	 * 是否必填：是
	 * 类型：String(16)
	 * 示例值：123.12.12.123
	 * 描述：APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "spbill_create_ip")
	@JacksonXmlCData(value = true)
	private String spbillCreateIp;
	/**
	 * <pre>
	 * 字段名：交易起始时间.
	 * 变量名：time_start
	 * 是否必填：否
	 * 类型：String(14)
	 * 示例值：20091225091010
	 * 描述：订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "time_start")
	@JacksonXmlCData(value = true)
	private String timeStart;
	/**
	 * <pre>
	 * 字段名：交易结束时间.
	 * 变量名：time_expire
	 * 是否必填：否
	 * 类型：String(14)
	 * 示例值：20091227091010
	 * 描述：订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
	 *   注意：最短失效时间间隔必须大于5分钟
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "time_expire")
	@JacksonXmlCData(value = true)
	private String timeExpire;
	/**
	 * <pre>
	 * 字段名：商品标记.
	 * 变量名：goods_tag
	 * 是否必填：否
	 * 类型：String(32)
	 * 示例值：WXG
	 * 描述：商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "goods_tag")
	@JacksonXmlCData(value = true)
	private String goodsTag;
	/**
	 * <pre>
	 * 字段名：通知地址.
	 * 变量名：notify_url
	 * 是否必填：是
	 * 类型：String(256)
	 * 示例值：http://www.weixin.qq.com/wxpay/pay.php
	 * 描述：接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "notify_url")
	@JacksonXmlCData(value = true)
	private String notifyUrl;
	/**
	 * <pre>
	 * 字段名：交易类型.
	 * 变量名：trade_type
	 * 是否必填：是
	 * 类型：String(16)
	 * 示例值： JSAPI
	 * 描述： 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定:
	 * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "trade_type")
	@JacksonXmlCData(value = true)
	private String tradeType;
	/**
	 * <pre>
	 * 字段名：商品Id.
	 * 变量名：product_id
	 * 是否必填：否
	 * 类型：String(32)
	 * 示例值：12235413214070356458058
	 * 描述：trade_type=NATIVE，此参数必传。此id为二维码中包含的商品Id，商户自行定义。
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "product_id")
	@JacksonXmlCData(value = true)
	private String productId;
	/**
	 * <pre>
	 * 字段名：指定支付方式.
	 * 变量名：limit_pay
	 * 是否必填：否
	 * 类型：String(32)
	 * 示例值：no_credit
	 * 描述：no_credit--指定不能使用信用卡支付
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "limit_pay")
	@JacksonXmlCData(value = true)
	private String limitPay;
	/**
	 * <pre>
	 * 字段名：用户标识.
	 * 变量名：openid
	 * 是否必填：否
	 * 类型：String(128)
	 * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 * 描述：trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
	 * openid如何获取，可参考【获取openid】。
	 * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "openid")
	@JacksonXmlCData(value = true)
	private String openid;
	/**
	 * <pre>
	 * 字段名：电子发票入口开放标识.
	 * 变量名：	receipt
	 * 是否必填：否
	 * 类型：String(8)
	 * 示例值：Y
	 * 描述：	Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "receipt")
	@JacksonXmlCData(value = true)
	private String receipt;
	/**
	 * <pre>
	 * 字段名：场景信息.
	 * 变量名：scene_info
	 * 是否必填：否，对H5支付来说是必填
	 * 类型：String(256)
	 * 示例值：{
	 * "store_id": "SZT10000",
	 * "store_name":"腾讯大厦腾大餐厅"
	 * }
	 * 描述：该字段用于统一下单时上报场景信息，目前支持上报实际门店信息。
	 * {
	 * "store_id": "", //门店唯一标识，选填，String(32)
	 * "store_name":"”//门店名称，选填，String(64)
	 * }
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "scene_info")
	@JacksonXmlCData(value = true)
	private String sceneInfo;
	
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getSceneInfo() {
		return sceneInfo;
	}

	public void setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
	}

}
