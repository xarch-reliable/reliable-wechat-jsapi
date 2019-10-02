package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.doman.JsApiParams;
import org.xarch.reliable.service.JsApiManager;
import org.xarch.reliable.service.QrCodeService;
import org.xarch.reliable.utils.BaseResultTools;

@RestController
public class JsapiController {

	@Autowired
	private JsApiManager jsApiManager;
	
	@Autowired
	private QrCodeService qrCodeService;

	@RequestMapping("jsapi/share/signature")
	public Map<String, Object> Support(@RequestParam(value = "url", required = true) String url) {
		JsApiParams jsApiParams = jsApiManager.signature(url);
		return BaseResultTools.ObjectToMap(jsApiParams);
	}
	 
	 @RequestMapping("/push/qrcode/to/user")
	  public Map<String, Object> PushQrCode(@RequestParam(value = "actid", required = true) String actid,
			  @RequestParam(value = "openid", required = true) String openid) throws Exception{
	    return qrCodeService.CreateQRCodeAPush(actid, "true", 400, openid);
	 }
}
