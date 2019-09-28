package org.xarch.reliable.service.thread;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.service.feign.FeignPayManager;

@Component
public class ThreadPool {

	@Autowired
	private FeignDataManager feignDataManager;

	@Autowired
	private FeignPayManager feignPayManager;

	@Async("asyncExecutor")
	public void StorageActInfoThread(Map<String, Object> sendata) {
		feignDataManager.doSupport2DataCenter(sendata);
	}
	
	@Async("asyncExecutor")
	public void RefundThread(String payid) {
		feignPayManager.getPayRefund(payid);
	}

	@Async("asyncExecutor")
	public void PayToUserThread(String openid, String partnerTradeNo) {
		feignPayManager.toPay(openid, partnerTradeNo);
	}
}
