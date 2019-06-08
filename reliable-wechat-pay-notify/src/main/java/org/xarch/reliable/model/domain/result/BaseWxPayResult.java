package org.xarch.reliable.model.domain.result;

import javax.persistence.MappedSuperclass;

import org.xarch.reliable.model.domain.request.BaseWxPayRequest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信支付结果共用属性类.
 * 
 * @author Wei
 *
 */
@MappedSuperclass
public abstract class BaseWxPayResult extends BaseWxPayRequest {

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

}
