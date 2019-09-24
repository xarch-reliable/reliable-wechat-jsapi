package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> payidmap = payIdCacheServer.getActidMap(actid, new HashMap<String, String>());
		String addMsg = payidmap.get(openid);
		if(addMsg == null) {
			Random random = new Random();
			int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
			String outTradeNo = String.valueOf(System.currentTimeMillis()) + rannum;
			payidmap.put(openid, outTradeNo);
			payIdCacheServer.setActidMap(actid, payidmap);
			resmap.put(openid, outTradeNo);
		}else {
			resmap.put("error_msg", "false");
		}
		return resmap;
	}

	@Override
	public Map<String, String> getActid2Openid2Map(String actid) {
		return payIdCacheServer.getActidMap(actid, new HashMap<String, String>());
	}

}
