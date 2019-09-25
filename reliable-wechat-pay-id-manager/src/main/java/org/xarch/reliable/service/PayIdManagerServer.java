package org.xarch.reliable.service;

import java.util.Map;

public interface PayIdManagerServer {

	public Map<String, Object> setActid2Openid(String actid,String openid, String out_trade_no);
	
	public Map<String, Object> getActid2Openid2Map(String actid);
}
