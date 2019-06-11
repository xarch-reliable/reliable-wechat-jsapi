package org.xarch.reliable.service.jsapi.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.weixin.WxConfig;
import org.xarch.reliable.model.doman.jsapi.JsApiParams;
import org.xarch.reliable.service.feign.FeignCentralManager;
import org.xarch.reliable.service.jsapi.JsApiManager;
import org.xarch.reliable.utils.RandomStringGenerator;

@Service
public class JsApiManagerImpl implements JsApiManager {

	private static final Logger logger = LoggerFactory.getLogger(JsApiManagerImpl.class);

	@Autowired
	private FeignCentralManager feignCentralManager;

	@Autowired
	private WxConfig wxConfig;

	@Override
	public JsApiParams signature(String url) {
		String Ticket = feignCentralManager.getTicket();
		logger.info("Ticket = [" + Ticket+"]");
		StringBuffer signatureSource = new StringBuffer();
		String nonceStr = RandomStringGenerator.generate();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		signatureSource.append("jsapi_ticket=").append(Ticket);
		signatureSource.append("&noncestr=").append(nonceStr);
		signatureSource.append("&timestamp=").append(timestamp);
		signatureSource.append("&url=").append(url);
		String signature = DigestUtils.sha1Hex(signatureSource.toString());
		return new JsApiParams(wxConfig.getAppId(), url, nonceStr, timestamp, signature, "share");
	}

}
