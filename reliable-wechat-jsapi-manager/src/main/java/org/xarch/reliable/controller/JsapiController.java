package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.doman.jsapi.JsApiParams;
import org.xarch.reliable.service.jsapi.JsApiManager;
import org.xarch.reliable.utils.BaseResultTools;

@RestController
public class JsapiController {

	@Autowired
	private JsApiManager jsApiManager;

	@RequestMapping("jsapi/share/signature")
	public Map<String, Object> Support(@RequestParam(value = "url", required = true) String url) {
		JsApiParams jsApiParams = jsApiManager.signature(url);
		return BaseResultTools.ObjectToMap(jsApiParams);
	}
}
