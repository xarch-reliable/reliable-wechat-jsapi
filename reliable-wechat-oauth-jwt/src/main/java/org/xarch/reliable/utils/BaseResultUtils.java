package org.xarch.reliable.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseResultUtils {

	private static final Logger logger = LoggerFactory.getLogger(BaseResultUtils.class);
	private static final ObjectMapper oMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static <T> T fromJSON(String jsonlString, Class<T> clz) {
		try {
			return oMapper.readValue(jsonlString, clz);
		} catch (Exception e) {
			logger.error("[xml]-->[pojo]失败：" + jsonlString);
			e.printStackTrace();
		}
		return (T) new Object();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> JsonObjectToMap(Object object) {
		return oMapper.convertValue(object, Map.class);
	}
	
	public static String MapToJsonStr(Map<String, Object> map) {
		try {
			return oMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("[xml]-->[JsonString]失败");
			e.printStackTrace();
		}
		return new String();
	}

	public static String JsonObjectToStr(Object object) {
		try {
			return oMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("[pojo]-->[JsonString]序列化失败");
			e.printStackTrace();
		}
		return new String();
	}

}
