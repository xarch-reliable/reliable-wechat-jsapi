package org.xarch.reliable.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.weixin.WxPayConfig;
import org.xarch.reliable.model.domain.constant.WxPayConstants.SignType;
import org.xarch.reliable.model.domain.constant.WxPayConstants.TradeType;
import org.xarch.reliable.model.domain.request.WechatPayToUserRequest;
import org.xarch.reliable.model.domain.request.WxPayRefundRequest;
import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;
import org.xarch.reliable.model.domain.result.WechatPayToUserResponse;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;
import org.xarch.reliable.model.domain.result.WxPayUnifiedOrderResult;
import org.xarch.reliable.model.domain.result.h5.WxPayMpOrderResult;
import org.xarch.reliable.service.BaseWxPayService;
import org.xarch.reliable.service.thread.ThreadPool;
import org.xarch.reliable.utils.BaseResultTools;
import org.xarch.reliable.utils.http.WxPayHttpUtils;
import org.xarch.reliable.utils.sign.SignUtils;

import reactor.core.publisher.Mono;

@Service
public class BaseWxPayServiceImpl implements BaseWxPayService {

	private static final Logger logger = LoggerFactory.getLogger(BaseWxPayServiceImpl.class);

	@Autowired
	private WxPayConfig wxPayConfig;

	@Autowired
	private ThreadPool threadPool;

	private CloseableHttpClient httpClient;
	private RequestConfig requestConfig;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Mono<T> createOrder(WxPayUnifiedOrderRequest request) throws Exception {
		Mono<WxPayUnifiedOrderResult> unifiedOrderResult = this.unifiedOrder(request);
		return unifiedOrderResult.flatMap(r -> {
			String prepayId = r.getPrepayId();
			if (StringUtils.isBlank(prepayId)) {
				logger.error("无法获取prepay id，错误代码： " + r.getErrCode() + "，信息：" + r.getErrCodeDes());
				return Mono.just((T) new Object());
			}
			threadPool.storagePayUOResultThread(r);
			String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
			String nonceStr = String.valueOf(System.currentTimeMillis());
			switch (request.getTradeType()) {
			case TradeType.MWEB:
				// TODO
				return null;
			case TradeType.NATIVE:
				// TODO
				return null;
			case TradeType.APP:
				// TODO
				return null;
			case TradeType.JSAPI:
				String signType = SignType.MD5;
				String appid = r.getAppid();
				WxPayMpOrderResult payResult = new WxPayMpOrderResult();
				payResult.setAppId(appid);
				payResult.setNonceStr(nonceStr);
				payResult.setPackageValue("prepay_id=" + prepayId);
				payResult.setSignType(signType);
				payResult.setTimeStamp(timestamp);
				// TODO
				payResult.setPaySign(SignUtils.createSign(payResult, signType, wxPayConfig.getMchKey(), new String[0]));
				payResult.setSignature(r.getSign());
				return Mono.just((T) payResult);
			default:
				logger.error("该交易类型暂不支持");
				return Mono.just((T) new Object());
			}
		});
	}

	@Override
	public Mono<WxPayUnifiedOrderResult> unifiedOrder(WxPayUnifiedOrderRequest request) throws Exception {
		request.setSign(SignUtils.createSign(request, request.getSignType(), wxPayConfig.getMchKey(), new String[0]));
		String requestContent = BaseResultTools.XmlObjectToStr(request);
		threadPool.storagePayUORequestThread(request);
		return WxPayHttpUtils.post(requestContent).flatMap(res -> {
			logger.info("[UnifiedOrder::request]+++[" + requestContent + "]");
			logger.info("[UnifiedOrder::response]===[" + res + "]");
			return Mono.just(BaseResultTools.fromXML(res, WxPayUnifiedOrderResult.class));
		});
	}

	@Override
	public Mono<WxPayRefundResult> refund(WxPayRefundRequest request) throws Exception {
		request.setSign(SignUtils.createSign(request, request.getSignType(), wxPayConfig.getMchKey(), new String[0]));
		String requestContent = BaseResultTools.XmlObjectToStr(request);
		threadPool.storagePayRefundRequestThread(request);
		return WxPayHttpUtils.post(requestContent, requestConfig, httpClient).flatMap(res -> {
			logger.info("[Refund::request]+++[" + requestContent + "]");
			logger.info("[Refund::response]===[" + res + "]");
			return Mono.just(BaseResultTools.fromXML(res, WxPayRefundResult.class)).flatMap(r -> {
				threadPool.storagePayRefundResultThread(r);
				return Mono.just(r);
			});
		});
	}

	@Bean
	private ReactorClientHttpConnector initpkc() {
		try {
			String key = wxPayConfig.getMchId();
			String filePath = wxPayConfig.getFilePath();
			this.httpClient = WxPayHttpUtils.initCert(key, filePath);
			this.requestConfig = WxPayHttpUtils.initRequestConfig();
		} catch (Exception e) {
			logger.error("证书初始化错误");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mono<WechatPayToUserResponse> payToUser(WechatPayToUserRequest request) throws Exception {
		String requestContent = BaseResultTools.XmlObjectToStr(request);
		return WxPayHttpUtils.toUserpost(requestContent, requestConfig, httpClient).flatMap(res -> {
			logger.info("[企业付款到零钱::request]+++[" + requestContent + "]");
			logger.info("[企业付款到零钱::response]===[" + res + "]");
			return Mono.just(BaseResultTools.fromXML(res, WechatPayToUserResponse.class)).flatMap(r -> {
				return Mono.just(r);
			});
		});
	}

}
