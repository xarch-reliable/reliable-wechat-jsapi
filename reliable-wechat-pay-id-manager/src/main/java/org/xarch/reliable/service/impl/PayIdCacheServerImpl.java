package org.xarch.reliable.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.PayIdCacheServer;

@Service
public class PayIdCacheServerImpl implements PayIdCacheServer {

	@Override
	public Map<String, String> setActidMap(String actid, Map<String, String> map) {
		return map;
	}

	@Override
	public Map<String, String> getActidMap(String actid, Map<String, String> map) {
		return map;
	}

}
