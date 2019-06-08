package org.xarch.reliable.service;

import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface PayIdCacheServer {

	@CachePut(cacheNames = "Actid2Openid2Payid", key = "#actid")
	public Map<String, String> setActidMap(String actid, Map<String, String> map);

	@Cacheable(cacheNames = "Actid2Openid2Payid", key = "#actid")
	public Map<String, String> getActidMap(String actid, Map<String, String> map);
}
