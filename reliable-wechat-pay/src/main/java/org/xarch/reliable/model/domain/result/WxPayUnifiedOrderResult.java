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
 * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果<br/>
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
public class WxPayUnifiedOrderResult extends BaseWxPayResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	 */
	@JacksonXmlProperty(localName = "prepay_id")
	@JacksonXmlCData(value = true)
	private String prepayId;

	/**
	 * 交易类型，取值为：JSAPI，NATIVE，APP等
	 */
	@JacksonXmlProperty(localName = "trade_type")
	@JacksonXmlCData(value = true)
	private String tradeType;

	/**
	 * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
	 */
	@JacksonXmlProperty(localName = "code_url")
	@JacksonXmlCData(value = true)
	private String codeURL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getCodeURL() {
		return codeURL;
	}

	public void setCodeURL(String codeURL) {
		this.codeURL = codeURL;
	}

}
