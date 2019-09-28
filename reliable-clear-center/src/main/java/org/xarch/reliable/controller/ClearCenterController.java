package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.ClearServer;
import org.xarch.reliable.utils.BaseResultTools;

@RestController
@RequestMapping("/reliable/clear")
public class ClearCenterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClearCenterController.class);

	@Autowired
	private ClearServer clearServer;
	
	@RequestMapping("/support")
	public Map<String, Object> ClearSupport(@RequestBody Map<String, Object> requestdata) {
		logger.info("DataCenterController::Support() : request = " + BaseResultTools.JsonObjectToStr(requestdata));
		return clearServer.parseClearData(requestdata);
	}
}
