package org.xarch.reliable.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.MediaUpload;
import org.xarch.reliable.service.CheckQrCodeService;
import org.xarch.reliable.service.feign.FeignMessageManager;
import org.xarch.reliable.service.thread.ThreadPool;
import org.xarch.reliable.utils.QRCodeUtil;

@Service
public class CheckQrCodeServiceImpl implements CheckQrCodeService {
	
	private static final String qrUrlBase = "https://www.xarchgroup.net/test/dist/index.html#qrcode";
	private static final String imagePathBase = "/root/images/";
	private static final String logoPath = "/root/images/logo.jpg";
	
	@Autowired
	private MediaUpload mediaUpload;
	
	@Autowired
	private FeignMessageManager feignMessageManager;
	
	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
	@Override
	public Map<String, Object> CreateQRCodeAPush(String actid, String key, Integer imageSize, String openid) throws Exception {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String qrUrl = qrUrlBase+"?actid="+actid+"&key="+key;
		String imagePath = imagePathBase+actid+"check.jpg";
		String media_id = null;
		if(QRCodeUtil.zxingCodeCreate(qrUrl, imagePath, imageSize, logoPath)) {
			media_id = mediaUpload.UploadMeida(imagePath, "image");
		}else {
			resmap.put("error_msg", "二维码创建失败");
		}
		if(media_id != null) {
			Map<String, Object> tmpmap = new HashMap<String, Object>();
			tmpmap.put("media_id", media_id);
			Map<String, Object> senMsgmap = new HashMap<String, Object>();
			senMsgmap.put("touser", openid);
			senMsgmap.put("msgtype", "image");
			senMsgmap.put("data", tmpmap);
			feignMessageManager.sendCustomerMsg(senMsgmap);
			resmap.put("success_msg", "true");
		}else {
			resmap.put("error_msg", "临时素材上传失败");
		}
		
		//threadPool.DeleteFolder(imagePath);
		
		return resmap;
	}

}
