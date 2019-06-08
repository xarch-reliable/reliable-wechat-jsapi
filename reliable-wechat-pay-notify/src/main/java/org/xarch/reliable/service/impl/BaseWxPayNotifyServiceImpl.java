package org.xarch.reliable.service.impl;

import org.springframework.stereotype.Service;
import org.xarch.reliable.model.domain.notify.response.WxPayNotifyResult;
import org.xarch.reliable.service.BaseWxPayNotifyService;

@Service
public class BaseWxPayNotifyServiceImpl implements BaseWxPayNotifyService {

	private static final String FAIL = "FAIL";
	private static final String SUCCESS = "SUCCESS";

	@Override
	public WxPayNotifyResult fail(String msg) {
		WxPayNotifyResult result = new WxPayNotifyResult();
		result.setReturnCode(FAIL);
		result.setReturnMsg(msg);
		return result;
	}

	@Override
	public WxPayNotifyResult success() {
		WxPayNotifyResult result = new WxPayNotifyResult();
		result.setReturnCode(SUCCESS);
		result.setReturnMsg("OK");
		return result;
	}

}
