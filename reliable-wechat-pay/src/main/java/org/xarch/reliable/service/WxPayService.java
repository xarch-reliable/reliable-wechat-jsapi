package org.xarch.reliable.service;

import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;
import org.xarch.reliable.model.domain.result.h5.WxPayMpOrderResult;

import reactor.core.publisher.Mono;

public interface WxPayService {

	public Mono<WxPayMpOrderResult> testUnifiedOrder(WxPayUnifiedOrderRequest request) throws Exception;

	public Mono<WxPayMpOrderResult> testCreateOrder_jsapi(String body, String outTradeNo, String totalFee, String ip,
			String openid, String detail, String attach);
	
	public Mono<WxPayRefundResult> refund(String outRefundNo,String outTradeNo);
	
	public Mono<WxPayRefundResult> refund_jsapi(String outRefundNo,String outTradeNo) throws Exception;
}
