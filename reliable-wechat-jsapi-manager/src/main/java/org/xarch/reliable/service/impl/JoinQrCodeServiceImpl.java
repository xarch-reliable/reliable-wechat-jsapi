package org.xarch.reliable.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.JoinQrCodeService;
import org.xarch.reliable.utils.WebHttpUtil;
import com.alibaba.fastjson.JSONObject;
import reactor.core.publisher.Mono;

@Service
public class JoinQrCodeServiceImpl implements JoinQrCodeService{

	private static final Logger logger = LoggerFactory.getLogger(JoinQrCodeServiceImpl.class);
	
	@Autowired
	private WebHttpUtil webHttpUtil;
	
	/*
	@Autowired
	private MediaUpload mediaUpload;
	
	@Autowired
	private FeignMessageManager feignMessageManager;
	
	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	*/
	
	@Override
	public Mono<Map<String, Object>> createForeverTicket(String actid) {
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		
        JSONObject sceneJsonObj = new  JSONObject();
        sceneJsonObj.put("scene_str", actid);
        JSONObject actionInfoJsonObj = new  JSONObject();
        actionInfoJsonObj.put("scene", sceneJsonObj);
        JSONObject sendJsonObj = new  JSONObject();
        sendJsonObj.put("expire_seconds", 604800);
        sendJsonObj.put("action_name", "QR_STR_SCENE");
        sendJsonObj.put("action_info", actionInfoJsonObj);
        return webHttpUtil.CreateWechatQrCodeWebPost(sendJsonObj).flatMap(r -> {
        	String ticket = r.getTicket();
        	if(ticket != null) {
	        	String imagePath = getQrcode(ticket, actid);
	        	logger.info("图片地址"+imagePath);
	        	resmap.put("success_msg", imagePath);
	        	/*
	        	String media_id = null;
				try {
					media_id = mediaUpload.UploadMeida(imagePath, "image");
				} catch (Exception e) {
					e.printStackTrace();
					resmap.put("error_msg", "临时素材上传失败");
					return Mono.just(resmap);
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
	    		 */
        	}else {
        		resmap.put("error_msg", "获取ticket失败");
        	}
    		return Mono.just(resmap);
        });
	}

	@Override
	public String getQrcode(String ticket, String actid) {
		String filepath = null;
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		String savepath = "/root/images/share/";
		try
		{
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			
			// 将ticket 作为文件名
			filepath = savepath + actid + "share.jpg";
 
			// 将微信服务器返回的输入流写入文件
 
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filepath));
 
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
 
			System.out.println(conn);
			conn.disconnect();
			System.out.println("根据ticket换取二维码成功");
		} catch (Exception e)
		{
			filepath = null;
			System.out.println("根据ticket换取二维码失败" + e);
		}
		
		return filepath;
	}

}
