package org.xarch.reliable.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseResultTools {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseResultTools.class);
	private static final ObjectMapper oMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static <T> T fromJSON(String jsonlString, Class<T> clz) {
		try {
			oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return oMapper.readValue(jsonlString, clz);
		} catch (Exception e) {
			logger.error("[string]-->[pojo]失败：" + jsonlString);
			e.printStackTrace();
		}
		return (T) new Object();
	}

	public static <T> T MapToObject(Map<String, Object> map, Class<T> clz) {
		oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return oMapper.convertValue(map, clz);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> ObjectToMap(Object object) {
		oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// oMapper.setSerializationInclusion(Include.NON_NULL);
		return oMapper.convertValue(object, Map.class);
	}

	public static String JsonObjectToStr(Object object) {
		try {
		
			oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// oMapper.setSerializationInclusion(Include.NON_NULL);
			return oMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("[pojo]-->[JsonString]序列化失败");
			e.printStackTrace();
		}
		return new String();
	}


}
