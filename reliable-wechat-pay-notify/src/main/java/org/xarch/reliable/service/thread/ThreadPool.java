package org.xarch.reliable.service.thread;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.notify.WxPayOrderNotifyResult;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyReqInfo;
import org.xarch.reliable.model.repository.PayOrderNotifyRepository;
import org.xarch.reliable.model.repository.PayRefundNotifyRepository;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.utils.transform.BaseResultTools;

@Component
public class ThreadPool {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

	@Autowired
	private FeignDataManager feignDataManager;
	
	@Autowired
	private PayOrderNotifyRepository payOrderNotify;

	@Autowired
	private PayRefundNotifyRepository payRefundNotify;

	
	@Async("asyncExecutor")
	public void storagePayOrderNotifyThread(WxPayOrderNotifyResult wxPayOrderNotifyResult) {
		Map<String, Object> sendmap = new HashMap<String, Object>();
		sendmap.put("xrdataction", "setOrderNotify");
		sendmap.put("data", BaseResultTools.XmlObjectToMap(wxPayOrderNotifyResult));
		logger.info("[feign存储OrderRequest]"+BaseResultTools.JsonObjectToStr(sendmap));
		feignDataManager.doSupport2DataCenter(sendmap);
	}

	@Async("asyncExecutor")
	public void storagePayRefundNotifyThread(WxPayRefundNotifyReqInfo wxPayRefundNotifyReqInfo) {
		Map<String, Object> sendmap = new HashMap<String, Object>();
		sendmap.put("xrdataction", "setRefundNotify");
		sendmap.put("data", BaseResultTools.XmlObjectToMap(wxPayRefundNotifyReqInfo));
		
		logger.info("[feign存储OrderResponse]"+BaseResultTools.JsonObjectToStr(sendmap));
		
		feignDataManager.doSupport2DataCenter(sendmap);
	}


	////////////////////////////////
	
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
