package org.xarch.reliable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.weixin.WxPayConfig;
import org.xarch.reliable.model.domain.notify.WxPayOrderNotifyResult;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyReqInfo;
import org.xarch.reliable.service.BaseWxPayNotifyService;
import org.xarch.reliable.service.WxPayNotifyService;
import org.xarch.reliable.service.thread.ThreadPool;
import org.xarch.reliable.utils.transform.BaseResultTools;
import org.xarch.reliable.utils.transform.RefundNotifyTools;

@Service
public class WxPayNotifyServiceImpl implements WxPayNotifyService {

	@Autowired
	private BaseWxPayNotifyService baseWxPayNotifyService;

	@Autowired
	private ThreadPool threadPool;

	@Autowired
	private WxPayConfig wxPayConfig;

	@Override
	public String ProcessOrderNotify(String notifyStr) {
		WxPayOrderNotifyResult result = BaseResultTools.fromXML(notifyStr, WxPayOrderNotifyResult.class);
		threadPool.StoragePayOrderNotifyThread(result);
		return BaseResultTools.XmlObjectToStr(baseWxPayNotifyService.success());
	}

	@Override
	public String ProcessRefundNotify(String notifyStr) {
		WxPayRefundNotifyReqInfo result = RefundNotifyTools.fromXML(notifyStr, wxPayConfig.getMchKey());
		threadPool.StoragePayRefundNotifyThread(result);
		return BaseResultTools.XmlObjectToStr(baseWxPayNotifyService.success());
	}

}
