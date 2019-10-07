package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.doman.JsApiParams;
import org.xarch.reliable.service.JsApiManager;
import org.xarch.reliable.service.CheckQrCodeService;
import org.xarch.reliable.service.JoinQrCodeService;
import org.xarch.reliable.utils.BaseResultTools;

import reactor.core.publisher.Mono;

@RestController
public class JsapiController {

	@Autowired
	private JsApiManager jsApiManager;
	
	@Autowired
	private CheckQrCodeService checkQrCodeService;
	
	@Autowired
	private JoinQrCodeService joinQrCodeService;

	@RequestMapping("jsapi/share/signature")
	public Map<String, Object> Support(@RequestParam(value = "url", required = true) String url) {
		JsApiParams jsApiParams = jsApiManager.signature(url);
		return BaseResultTools.ObjectToMap(jsApiParams);
	}
	 
	 @RequestMapping("/push/check/qrcode/to/user")
	  public Map<String, Object> PushCheckQrCode(@RequestParam(value = "actid", required = true) String actid) throws Exception{
		 return checkQrCodeService.CreateQRCodeAPush(actid, "true", 250);
	 }
	 
	 @RequestMapping("/push/share/qrcode/to/user")
	  public Mono<Map<String, Object>> PushJoinQrCode(@RequestParam(value = "actid", required = true) String actid) throws Exception{
		 checkQrCodeService.CreateQRCodeAPush(actid, "true", 250);
		 return joinQrCodeService.createForeverTicket(actid);
	    
	 }
}
