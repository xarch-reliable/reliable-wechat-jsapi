package org.xarch.reliable.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.weixin.WxPayConfig;
import org.xarch.reliable.model.domain.request.WechatPayToUserRequest;
import org.xarch.reliable.model.domain.request.WxPayRefundRequest;
import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;
import org.xarch.reliable.model.domain.result.WechatPayToUserResponse;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;
import org.xarch.reliable.model.domain.result.h5.WxPayMpOrderResult;
import org.xarch.reliable.service.BaseWxPayService;
import org.xarch.reliable.service.WxPayService;
import org.xarch.reliable.utils.sign.SignUtils;

import reactor.core.publisher.Mono;

@Service
public class WxPayServiceImpl implements WxPayService {

	private static final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

	@Autowired
	private WxPayConfig wxPayConfig;

	@Autowired
	private BaseWxPayService baseWxPayService;

	@Override
	public Mono<WxPayMpOrderResult> testUnifiedOrder(WxPayUnifiedOrderRequest request) throws Exception {
		request.setAppid(wxPayConfig.getAppId());
		request.setMchId(wxPayConfig.getMchId());
		request.setTradeType(wxPayConfig.getTradeType());
		request.setNotifyUrl(wxPayConfig.getUnifiedUrl());
		request.setSignType(wxPayConfig.getSignType());
		return baseWxPayService.createOrder(request);
	}

	@Override
	public Mono<WxPayMpOrderResult> testCreateOrder_jsapi(String body, String outTradeNo, String totalFee, String ip,
			String openid, String detail, String attach) {
		try {
			WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
			request.setBody(body);
			request.setNonceStr(String.valueOf(System.currentTimeMillis()));
			request.setOutTradeNo(outTradeNo);
			request.setTotalFee(totalFee);
			request.setSpbillCreateIp(ip);
			request.setOpenid(openid);
			request.setDetail(detail);
			request.setAttach(attach);
			return this.testUnifiedOrder(request);
		} catch (Exception e) {
			logger.error("BaseWxPayService-->支付请求发生错误");
			e.printStackTrace();
		}
		return Mono.just(new WxPayMpOrderResult());
	}

	@Override
	public Mono<WxPayRefundResult> refund_jsapi(String outRefundNo, String outTradeNo) throws Exception {
		WxPayRefundRequest request = new WxPayRefundRequest();
		request.setAppid(wxPayConfig.getAppId());
		request.setMchId(wxPayConfig.getMchId());
		request.setNonceStr(String.valueOf(System.currentTimeMillis()));
		request.setNotifyUrl(wxPayConfig.getRefundUrl());
		request.setSignType(wxPayConfig.getSignType());
		request.setOutTradeNo(outTradeNo);
		request.setOutRefundNo(outRefundNo);
		request.setTotalFee("1");
		request.setRefundFee("1");
		request.setRefundFeeType("CNY");
		return baseWxPayService.refund(request);
	}

	@Override
	public Mono<WxPayRefundResult> refund(String outRefundNo, String outTradeNo) {
		try {
			return this.refund_jsapi(outRefundNo, outTradeNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mono.just(new WxPayRefundResult());
	}

	@Override
	public Mono<WechatPayToUserResponse> prePayToUser(String openid, String partnerTradeNo, String checkName, String reUserName, String amount, String desc, String spbillCreateIp) {
		try {
			WechatPayToUserRequest request = new WechatPayToUserRequest();
			request.setMchAppid(wxPayConfig.getAppId());
			request.setMchid(wxPayConfig.getMchId());
			request.setSignType(wxPayConfig.getSignType());
			request.setNonceStr(String.valueOf(System.currentTimeMillis()));
			request.setAmount(amount);
			request.setCheckName(checkName);
			request.setDesc(desc);
			request.setOpenid(openid);
			request.setPartnerTradeNo(partnerTradeNo);
			request.setReUserName(reUserName);
			request.setSign(SignUtils.createSign(request, request.getSignType(), wxPayConfig.getMchKey(), new String[0]));
			request.setSpbillCreateIp(spbillCreateIp);
			return baseWxPayService.payToUser(request);
		} catch (Exception e) {
			logger.error("BaseWxPayService : payToUser-->企业支付到零钱支付请求发生错误");
			e.printStackTrace();
		}
		return Mono.just(new WechatPayToUserResponse());
	}

}
