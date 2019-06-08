package org.xarch.reliable.utils.transform;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xarch.reliable.model.domain.constant.WxPayConstants;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyReqInfo;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyResult;

/**
 * 在申请退款接口中上传参数“notify_url”以开通该功能 <br/>
 * 如果链接无法访问，商户将无法接收到微信通知 <br/>
 * 通知url必须为直接可访问的url，不能携带参数 <br/>
 * notify_url：“https://pay.weixin.qq.com/wxpay/pay.action” <br/>
 * 
 * @author Wei
 *
 */
public class RefundNotifyTools {

	public static final String ALGORITHM_PKCS7 = "AES/ECB/PKCS7Padding";
	public static final String ALGORITHM_PKCS5 = "AES/ECB/PKCS5Padding";

	private static final Logger logger = LoggerFactory.getLogger(RefundNotifyTools.class);

	public static WxPayRefundNotifyReqInfo fromXML(String xmlString, String mchKey) {
		WxPayRefundNotifyResult result = BaseResultTools.fromXML(xmlString, WxPayRefundNotifyResult.class);
		if (WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
			logger.error("退款-->SUCCESS");
			WxPayRefundNotifyReqInfo reqInfo = getReqInfo(result, mchKey);
			reqInfo.setReturnCode(result.getResultCode());
			reqInfo.setReturnMsg(result.getReturnMsg());
			reqInfo.setAppid(result.getAppid());
			reqInfo.setMchId(result.getMchId());
			reqInfo.setNonceStr(result.getNonceStr());
			return reqInfo;
		} else {
			logger.error("退款-->请求失败");
			WxPayRefundNotifyReqInfo reqInfo = new WxPayRefundNotifyReqInfo();
			reqInfo.setReturnCode(result.getResultCode());
			reqInfo.setReturnMsg(result.getReturnMsg());
			return reqInfo;
		}
	}

	/**
	 * 解密步骤如下：<br/>
	 * <1>对加密串A做base64解码，得到加密串B<br/>
	 * <2>对商户key做md5，得到32位小写key* <br/>
	 * ( key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )<br/>
	 * <3>用key*对加密串B做AES-256-ECB解密（PKCS7Padding）<br/>
	 * 
	 * @param reqInfoString
	 * @param mchKey
	 * @return
	 */
	public static WxPayRefundNotifyReqInfo getReqInfo(WxPayRefundNotifyResult result, String mchKey) {
		try {
			final String keyMd5String = DigestUtils.md5Hex(mchKey).toLowerCase();
			SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance(ALGORITHM_PKCS5);
			cipher.init(Cipher.DECRYPT_MODE, key);

			String reqInfoString = result.getReqInfo();
			logger.error("[1]->[ReqInfo]=["+reqInfoString+"]");
			String infoString = new String(cipher.doFinal(Base64.decodeBase64(reqInfoString)));
			logger.error("[2]->[infoString]=["+infoString+"]");
			return BaseResultTools.fromXML(infoString, WxPayRefundNotifyReqInfo.class);
		} catch (Exception e) {
			logger.error("退款账单-->解析req_info失败->"+e.getMessage());
			e.printStackTrace();
		}
		return new WxPayRefundNotifyReqInfo();
	}

}
