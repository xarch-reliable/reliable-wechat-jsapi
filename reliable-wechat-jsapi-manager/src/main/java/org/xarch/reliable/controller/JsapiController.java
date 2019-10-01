package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.doman.JsApiParams;
import org.xarch.reliable.service.JsApiManager;
import org.xarch.reliable.service.MediaUpload;
import org.xarch.reliable.utils.BaseResultTools;
import org.xarch.reliable.utils.QRCodeUtil;

@RestController
public class JsapiController {

	@Autowired
	private JsApiManager jsApiManager;
	
	@Autowired
	private MediaUpload mediaUpload;

	@RequestMapping("jsapi/share/signature")
	public Map<String, Object> Support(@RequestParam(value = "url", required = true) String url) {
		JsApiParams jsApiParams = jsApiManager.signature(url);
		return BaseResultTools.ObjectToMap(jsApiParams);
	}
	
	@RequestMapping("/upload/meida")
	public Map<String, Object> Upload(@RequestParam(value = "filePath", required = true) String filePath,
			@RequestParam(value = "fileType", required = true) String fileType) throws Exception {
		return mediaUpload.UploadMeida(filePath, fileType);
	}
	
	 @RequestMapping(value = "/create/qrcode")
	  public Map<String, Object> createQrCode() throws Exception{
	    String qrUrl = "https://www.xarchgroup.net/test/dist/index.html#create";//扫描二维码跳转的链接
	    QRCodeUtil.zxingCodeCreate(qrUrl, "/root/images/test.jpg",500,"/root/images/logo.jpg");
	    return mediaUpload.UploadMeida("/root/images/test.jpg", "image");
	 }
}
