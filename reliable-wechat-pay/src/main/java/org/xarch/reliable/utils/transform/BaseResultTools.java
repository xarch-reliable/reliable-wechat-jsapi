package org.xarch.reliable.utils.transform;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class BaseResultTools {

	private static final Logger logger = LoggerFactory.getLogger(BaseResultTools.class);
	private static final XmlMapper xmlMapper = new XmlMapper();
	private static final ObjectMapper oMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xmlString, Class<T> clz) {
		try {
			return xmlMapper.readValue(xmlString, clz);
		} catch (IOException e) {
			logger.error("[xml]-->[pojo]失败：" + xmlString);
			e.printStackTrace();
		}
		return (T) new Object();
	}

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
	public static Map<String, String> XmlObjectToMap(Object object) {
		return xmlMapper.convertValue(object, Map.class);
	}

	public static <T> T MapToObject(Map<String, Object> map, Class<T> clz) {
		return oMapper.convertValue(map, clz);
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

	public static String XmlObjectToStr(Object object) {
		try {
			return xmlMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("[pojo]-->[XmlString]序列化失败");
			e.printStackTrace();
		}
		return new String();
	}

}
