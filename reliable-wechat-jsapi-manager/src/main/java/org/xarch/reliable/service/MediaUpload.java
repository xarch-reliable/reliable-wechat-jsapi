package org.xarch.reliable.service;

import java.util.Map;

public interface MediaUpload {

	public Map<String, Object> UploadMeida(String filePath,String fileType) throws Exception;
	
}
