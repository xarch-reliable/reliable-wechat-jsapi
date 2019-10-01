package org.xarch.reliable.controller.hystrix;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignCentralManager;


@Service
public class FeignCentralHystrix implements FeignCentralManager {

	@Override
	public Map<String, Object> getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTicket() {
		// TODO Auto-generated method stub
		return "getTicket->error";
	}

	
}
