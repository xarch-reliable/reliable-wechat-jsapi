package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.PayIdManagerServer;
import org.xarch.reliable.util.RedisUtil;

@Service
public class PayIdManagerServerImpl implements PayIdManagerServer {
	
	@Autowired 
	private RedisUtil redisUtil;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> setActid2Openid(String actid, String openid, String out_trade_no) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		Map tmap = redisUtil.hmget(actid);
		Map<String, Object> map = (Map<String, Object>)tmap;
		map.put(openid, out_trade_no);
		if(redisUtil.hmset(actid, map)) {
			resmap.put("success_msg", "true");
		}else {
			resmap.put("error_msg", "false");
		}
		return resmap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getActid2Openid2Map(String actid) {
		Map map = redisUtil.hmget(actid);
		return (Map<String, Object>)map;
	}

}
