package org.xarch.reliable.service.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.request.WxPayRefundRequest;
import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;
import org.xarch.reliable.model.domain.result.WxPayUnifiedOrderResult;
import org.xarch.reliable.model.repository.PayRefundRequestRepository;
import org.xarch.reliable.model.repository.PayRefundResultRepository;
import org.xarch.reliable.model.repository.PayUORequestRepository;
import org.xarch.reliable.model.repository.PayUOResultRepository;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.utils.transform.BaseResultTools;

@Component
public class ThreadPool {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	
	@Autowired
	private FeignDataManager feignDataManager;
	
	@Autowired
	private PayUORequestRepository payUORequest;

	@Autowired
	private PayUOResultRepository payUOResult;

	@Autowired
	private PayRefundRequestRepository payRefundRequest;

	@Autowired
	private PayRefundResultRepository payRefundResult;

	@Async("asyncExecutor")
	public void storagePayUORequestThread(WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest) {
		WxPayUnifiedOrderRequest existing = payUORequest.findByOutTradeNo(wxPayUnifiedOrderRequest.getOutTradeNo());
		if (existing != null) {
			return;
		}
		payUORequest.save(wxPayUnifiedOrderRequest);
	}

	@Async("asyncExecutor")
	public void storagePayUOResultThread(WxPayUnifiedOrderResult wxPayUnifiedOrderResult) {
		WxPayUnifiedOrderResult existing = payUOResult.findByPrepayId(wxPayUnifiedOrderResult.getPrepayId());
		if (existing != null) {
			return;
		}
		payUOResult.save(wxPayUnifiedOrderResult);
	}

	@Async("asyncExecutor")
	public void storagePayRefundRequestThread(WxPayRefundRequest wxPayRefundRequest) {
		WxPayRefundRequest existing = payRefundRequest.findByOutTradeNo(wxPayRefundRequest.getOutTradeNo());
		if (existing != null) {
			return;
		}
		payRefundRequest.save(wxPayRefundRequest);
	}

	@Async("asyncExecutor")
	public void storagePayRefundResultThread(WxPayRefundResult wxPayRefundResult) {
		WxPayRefundResult existing = payRefundResult.findByOutTradeNo(wxPayRefundResult.getOutTradeNo());
		if (existing != null) {
			return;
		}
		payRefundResult.save(wxPayRefundResult);
	}
	
	/////////////////////////////////
	
	@Async("asyncExecutor")
	public void StoragePayUORequestThread(WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest) {
		WxPayUnifiedOrderRequest existing = payUORequest.findByOutTradeNo(wxPayUnifiedOrderRequest.getOutTradeNo());
		if (existing != null) {
			return;
		}
		logger.info("[存储PayUORequest]"+BaseResultTools.JsonObjectToStr(BaseResultTools.XmlObjectToMap(wxPayUnifiedOrderRequest)));
		payUORequest.save(wxPayUnifiedOrderRequest);
	}

	@Async("asyncExecutor")
	public void StoragePayUOResultThread(WxPayUnifiedOrderResult wxPayUnifiedOrderResult) {
		WxPayUnifiedOrderResult existing = payUOResult.findByPrepayId(wxPayUnifiedOrderResult.getPrepayId());
		if (existing != null) {
			return;
		}
		logger.info("[存储PayUOResult]"+BaseResultTools.JsonObjectToStr(BaseResultTools.XmlObjectToMap(wxPayUnifiedOrderResult)));
		payUOResult.save(wxPayUnifiedOrderResult);
	}

	@Async("asyncExecutor")
	public void StoragePayRefundRequestThread(WxPayRefundRequest wxPayRefundRequest) {
		WxPayRefundRequest existing = payRefundRequest.findByOutTradeNo(wxPayRefundRequest.getOutTradeNo());
		if (existing != null) {
			return;
		}
		logger.info("[存储PayRefundRequest]"+BaseResultTools.JsonObjectToStr(BaseResultTools.XmlObjectToMap(wxPayRefundRequest)));
		payRefundRequest.save(wxPayRefundRequest);
	}

	@Async("asyncExecutor")
	public void StoragePayRefundResultThread(WxPayRefundResult wxPayRefundResult) {
		WxPayRefundResult existing = payRefundResult.findByOutTradeNo(wxPayRefundResult.getOutTradeNo());
		if (existing != null) {
			return;
		}
		logger.info("[存储PayRefundResult]"+BaseResultTools.JsonObjectToStr(BaseResultTools.XmlObjectToMap(wxPayRefundResult)));
		payRefundResult.save(wxPayRefundResult);
	}

}
