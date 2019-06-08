package org.xarch.reliable.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.notify.WxPayOrderNotifyResult;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyReqInfo;
import org.xarch.reliable.model.repository.PayOrderNotifyRepository;
import org.xarch.reliable.model.repository.PayRefundNotifyRepository;

@Component
public class ThreadPool {

	@Autowired
	private PayOrderNotifyRepository payOrderNotify;

	@Autowired
	private PayRefundNotifyRepository payRefundNotify;

	@Async("asyncExecutor")
	public void StoragePayOrderNotifyThread(WxPayOrderNotifyResult wxPayOrderNotifyResult) {
		 WxPayOrderNotifyResult existing = payOrderNotify.findByOutTradeNo(wxPayOrderNotifyResult.getOutTradeNo());
		if (existing != null) {
			return;
		}
		payOrderNotify.save(wxPayOrderNotifyResult);
	}

	@Async("asyncExecutor")
	public void StoragePayRefundNotifyThread(WxPayRefundNotifyReqInfo wxPayRefundNotifyReqInfo) {
	  WxPayRefundNotifyReqInfo existing = payRefundNotify.findByOutTradeNo(wxPayRefundNotifyReqInfo.getOutTradeNo());
		if (existing != null) {
			return;
		}
		payRefundNotify.save(wxPayRefundNotifyReqInfo);
	}

}
