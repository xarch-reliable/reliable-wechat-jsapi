package org.xarch.reliable.service;

import com.alibaba.fastjson.JSONObject;

public interface MediaUpload {

	public JSONObject UploadMeida(String filePath,String fileType) throws Exception;
	
}
