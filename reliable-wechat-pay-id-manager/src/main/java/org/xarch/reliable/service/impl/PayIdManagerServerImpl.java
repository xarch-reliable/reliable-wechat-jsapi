package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.PayIdCacheServer;
import org.xarch.reliable.service.PayIdManagerServer;

@Service
public class PayIdManagerServerImpl implements PayIdManagerServer {

	@Autowired
	private PayIdCacheServer payIdCacheServer;

	@Override
	public Map<String, String> addActid2Openid(String actid, String openid) {
		Map<String, String> map = payIdCacheServer.getActidMap(actid, new HashMap<String, String>());
		String outTradeNo = String.valueOf(System.currentTimeMillis());
		map.put(openid, outTradeNo);
		return payIdCacheServer.setActidMap(actid, map);
	}

	@Override
	public Map<String, String> getActid2Openid2Map(String actid) {
		return payIdCacheServer.getActidMap(actid, new HashMap<String, String>());
	}

}