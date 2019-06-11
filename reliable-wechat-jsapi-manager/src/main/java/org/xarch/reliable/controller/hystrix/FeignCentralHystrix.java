package org.xarch.reliable.controller.hystrix;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignCentralManager;


@Service
public class FeignCentralHystrix implements FeignCentralManager {

	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		return "getAccessToken->error";
	}

	@Override
	public String getTicket() {
		// TODO Auto-generated method stub
		return "getTicket->error";
	}

	
}
