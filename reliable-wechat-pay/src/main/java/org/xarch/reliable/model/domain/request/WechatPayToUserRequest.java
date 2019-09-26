package org.xarch.reliable.model.domain.request;

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
public class WechatPayToUserRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * <pre>
	 * 字段名：公众账号ID.
	 * 变量名：mch_appid
	 * 是否必填：是
	 * 类型：String(32)
	 * 示例值：wxd678efh567hg6787
	 * 描述：微信分配的公众账号ID（企业号corpid即为此appId）
	 * </pre>
	 */
	@JacksonXmlProperty(localName = "mch_appid")
	@JacksonXmlCData(value = true)
	protected String mchAppid;
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
	@JacksonXmlProperty(localName = "mchid")
	@JacksonXmlCData(value = true)
	protected String mchid;
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

	/**
	 * 商户订单号.
	 */
	@JacksonXmlProperty(localName = "partner_trade_no")
	@JacksonXmlCData(value = true)
	private String partnerTradeNo;

	/**
	 * 付款金额.
	 */
	@JacksonXmlProperty(localName = "amount")
	@JacksonXmlCData(value = true)
	private String amount;
	
	/**
	 * 用户openid.
	 */
	@JacksonXmlProperty(localName = "openid")
	@JacksonXmlCData(value = true)
	private String openid;
	
	/**
	 * 校验用户姓名选项.
	 */
	@JacksonXmlProperty(localName = "check_name")
	@JacksonXmlCData(value = true)
	private String checkName;
	
	/**
	 * 收款用户姓名.
	 */
	@JacksonXmlProperty(localName = "re_user_name")
	@JacksonXmlCData(value = true)
	private String reUserName;
	
	/**
	 * 企业付款备注.
	 */
	@JacksonXmlProperty(localName = "desc")
	@JacksonXmlCData(value = true)
	private String desc;
	
	/**
	 * Ip地址.
	 */
	@JacksonXmlProperty(localName = "spbill_create_ip")
	@JacksonXmlCData(value = true)
	private String spbillCreateIp;

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

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getReUserName() {
		return reUserName;
	}

	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}
