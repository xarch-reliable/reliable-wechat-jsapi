package org.xarch.reliable.service;

import org.xarch.reliable.model.domain.notify.response.WxPayNotifyResult;

/**
 * 支付相关接口
 * 
 * @author Wei
 *
 */
public interface BaseWxPayNotifyService {

	/**
	   * Fail string.
	   *
	   * @param msg the msg
	   * @return the string
	   */
	  public WxPayNotifyResult fail(String msg);
	  
	  /**
	   * Success string.
	   *
	   * @param msg the msg
	   * @return the string
	   */
	  public WxPayNotifyResult success();

}
