package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.PayIdManagerServer;

@RestController
@RequestMapping("/actid/to/payid")
public class Actid2OpenidController {
	private static final Logger logger = LoggerFactory.getLogger(Actid2OpenidController.class);

	@Autowired
	private PayIdManagerServer payIdManagerServer;

	@RequestMapping("/add")
	public Map<String, String> addMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid) {
		logger.info("Actid2OpenidController::addMap() : actid = "+actid+" openid = "+openid);
		return payIdManagerServer.addActid2Openid(actid, openid);
	}
	
	@RequestMapping("/get")
	public Map<String, String> getMap(@RequestParam(value = "actid", required = true) String actid) {
		return payIdManagerServer.getActid2Openid2Map(actid);
	}

}
