package org.xarch.reliable.utils.sign;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.xarch.reliable.model.domain.constant.WxPayConstants.SignType;
import org.xarch.reliable.utils.transform.BaseResultTools;

import com.google.common.collect.Lists;

public class SignUtils {

	/**
	 * 微信支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3).
	 *
	 * @param object        属性如果存在XML注解，则使用其作为key，否则使用变量名
	 * @param signType      签名类型，如果为空，则默认为MD5
	 * @param signKey       签名Key
	 * @param ignoredParams 签名时需要忽略的特殊参数
	 * @return 签名字符串 string
	 */
	public static String createSign(Object object, String signType, String signKey, String[] ignoredParams) {
		return createSign(BaseResultTools.XmlObjectToMap(object), signType, signKey, ignoredParams);
	}

	/**
	 * 微信支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3).
	 *
	 * @param params        参数信息
	 * @param signType      签名类型，如果为空，则默认为MD5
	 * @param signKey       签名Key
	 * @param ignoredParams 签名时需要忽略的特殊参数
	 * @return 签名字符串 string
	 */
	public static String createSign(Map<String, String> params, String signType, String signKey,
			String[] ignoredParams) {

		SortedMap<String, String> sortedMap = new TreeMap<>(params);

		StringBuilder toSign = new StringBuilder();
		for (String key : sortedMap.keySet()) {
			String value = params.get(key);
			boolean shouldSign = false;
			if (StringUtils.isNotEmpty(value) && !ArrayUtils.contains(ignoredParams, key)
					&& !Lists.newArrayList("sign", "key", "xmlString", "xmlDoc", "couponList").contains(key)) {
				shouldSign = true;
			}
			if (shouldSign) {
				toSign.append(key).append("=").append(value).append("&");
			}
		}
		toSign.append("key=").append(signKey);

		if (SignType.HMAC_SHA256.equals(signType)) {
			return SignUtils.createHmacSha256Sign(toSign.toString(), signKey);
		}
		return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
	}

	/**
	 * 校验签名是否正确.
	 *
	 * @param xmlBean  Bean需要标记有XML注解
	 * @param signType 签名类型，如果为空，则默认为MD5
	 * @param signKey  校验的签名Key
	 * @return true - 签名校验成功，false - 签名校验失败
	 */
	public static boolean checkSign(Object xml, String signType, String signKey) {
		return checkSign(BaseResultTools.XmlObjectToMap(xml), signType, signKey);
	}

	/**
	 * 校验签名是否正确.
	 *
	 * @param params   需要校验的参数Map
	 * @param signType 签名类型，如果为空，则默认为MD5
	 * @param signKey  校验的签名Key
	 * @return true - 签名校验成功，false - 签名校验失败
	 */
	public static boolean checkSign(Map<String, String> params, String signType, String signKey) {
		String sign = createSign(params, signType, signKey, new String[0]);
		return sign.equals(params.get("sign"));
	}

	/**
	 * HmacSHA256 签名算法
	 *
	 * @param message 签名数据
	 * @param key     签名密钥
	 */
	public static String createHmacSha256Sign(String message, String key) {
		try {
			Mac sha256 = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			sha256.init(secretKeySpec);
			byte[] bytes = sha256.doFinal(message.getBytes(StandardCharsets.UTF_8));
			return Hex.encodeHexString(bytes).toUpperCase();
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将单位为元转换为单位为分.
	 *
	 * @param yuan 将要转换的元的数值字符串
	 * @return the integer
	 */
	public static Integer yuanToFen(String yuan) {
		return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
	}

}
