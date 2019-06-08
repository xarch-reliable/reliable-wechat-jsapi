package org.xarch.reliable.service;

import org.xarch.reliable.model.domain.request.WxPayRefundRequest;
import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;
import org.xarch.reliable.model.domain.result.WxPayUnifiedOrderResult;

import reactor.core.publisher.Mono;

/**
 * 支付相关接口
 * 
 * @author Wei
 *
 */
public interface BaseWxPayService {

	/**
	 * 调用统一下单接口，并组装生成支付所需参数对象.
	 * 
	 * @param <T>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public <T> Mono<T> createOrder(WxPayUnifiedOrderRequest request) throws Exception;

	/**
	 * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1)
	 * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
	 * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
	 * 
	 * @param request
	 * @return
	 */
	public Mono<WxPayUnifiedOrderResult> unifiedOrder(WxPayUnifiedOrderRequest request) throws Exception;

	/**
	 * <pre>
	 * 微信支付-申请退款.
	 * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	 * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
	 * </pre>
	 *
	 * @param request 请求对象
	 * @return 退款操作结果 wx pay refund result
	 * @throws Exception
	 * @throws WxPayException the wx pay exception
	 */
	public Mono<WxPayRefundResult> refund(WxPayRefundRequest request) throws Exception;

}
