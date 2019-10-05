package org.xarch.reliable.service;

import java.util.Map;

public interface CheckQrCodeService {

	public Map<String, Object> CreateQRCodeAPush(String actid, String key, Integer imageSize, String openid) throws Exception;

}
