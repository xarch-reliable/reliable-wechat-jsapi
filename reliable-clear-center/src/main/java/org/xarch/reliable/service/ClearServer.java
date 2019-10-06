package org.xarch.reliable.service;

import java.util.Map;

public interface ClearServer {

	public Map<String, Object> parseClearData(Map<String, Object> data);
	
	public Map<String, Object> ClearProcess(Map<String, Object> ReliableMap, Map<String, Object> UnReliableMap, String actid, String distribution);
	
}
