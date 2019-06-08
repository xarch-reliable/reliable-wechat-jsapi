package org.xarch.reliable.model.domain.notify;

import java.io.Serializable;

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
public class WxPayRefundNotifyResult extends BaseWxPayResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 加密信息请用商户秘钥进行解密
	 */
	@JacksonXmlProperty(localName = "req_info")
	@JacksonXmlCData(value = true)
	private String reqInfo;

	public String getReqInfo() {
		return reqInfo;
	}

	public void setReqInfo(String reqInfo) {
		this.reqInfo = reqInfo;
	}

}
