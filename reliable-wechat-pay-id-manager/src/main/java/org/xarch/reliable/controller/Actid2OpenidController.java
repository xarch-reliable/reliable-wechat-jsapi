package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.PayIdManagerServer;

@RestController
@RequestMapping("/actid/to/payid")
public class Actid2OpenidController {
	
	@Autowired
	private PayIdManagerServer payIdManagerServer;

	@RequestMapping("/set")
	public Map<String, Object> setPayid(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "out_trade_no", required = true) String out_trade_no) {
		return payIdManagerServer.setActid2Openid(actid, openid, out_trade_no);
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getPayidMap(@RequestParam(value = "actid", required = true) String actid) {
		return payIdManagerServer.getActid2Openid2Map(actid);
	}

}
