package org.xarch.reliable.service;

import java.util.Map;

public interface DistributionService {

	public boolean RandomDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid);
	
	public boolean EqualDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid);
	
	public boolean SeparateDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid);
}
