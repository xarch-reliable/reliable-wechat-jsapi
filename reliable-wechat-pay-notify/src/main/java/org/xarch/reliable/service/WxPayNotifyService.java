package org.xarch.reliable.service;

public interface WxPayNotifyService {

	public String ProcessOrderNotify(String notifyStr);
	
	public String ProcessRefundNotify(String notifyStr);
}
