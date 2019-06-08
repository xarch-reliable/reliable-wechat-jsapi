package org.xarch.reliable.service;

import java.util.Map;

public interface PayIdManagerServer {

	public Map<String, String> addActid2Openid(String actid,String openid);
	
	public Map<String, String> getActid2Openid2Map(String actid);
}
