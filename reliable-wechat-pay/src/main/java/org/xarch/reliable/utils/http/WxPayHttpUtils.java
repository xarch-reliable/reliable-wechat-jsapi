package org.xarch.reliable.utils.http;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WxPayHttpUtils {

	private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
	private static final String PAY_UORDER_PATH = "/pay/unifiedorder";
	private static final String PAY_REFUND_PATH = "/secapi/pay/refund";
	private static final String PAY_TOUSER_PATH = "/mmpaymkttransfers/promotion/transfers";
	private static final String PKCS = "PKCS12";
	private static final int socketTimeout = 10000;// 连接超时时间，默认10秒
	private static final int connectTimeout = 30000;// 传输超时时间，默认30秒

	/**
	 * 统一下单请求
	 * 
	 * @param requestStr
	 * @param useKey
	 * @return
	 */
	public static Mono<String> post(String requestStr) {
		WebClient webClient = WebClient.builder().baseUrl(PAY_BASE_URL).build();
		return webClient.post().uri(PAY_UORDER_PATH).contentType(MediaType.APPLICATION_XML)
				.acceptCharset(Charset.forName("UTF-8")).body(Mono.just(requestStr), String.class).retrieve()
				.bodyToMono(String.class);
	}

	/**
	 * 退款请求
	 * 
	 * @param key
	 * @param requestStr
	 * @param useKey
	 * @return
	 * @throws Exception
	 */
	public static Mono<String> post(String requestStr, RequestConfig requestConfig, CloseableHttpClient httpClient)
			throws Exception {

		HttpPost httpPost = new HttpPost(PAY_BASE_URL + PAY_REFUND_PATH);
		httpPost.addHeader("Content-Type", "application/xml");
		StringEntity postEntity = new StringEntity(requestStr, "UTF-8");
		httpPost.setEntity(postEntity);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		String result = null;
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} finally {
			httpPost.abort();
		}
		return Mono.just(result);
	}

	/**
	 * 退款请求
	 * 
	 * @param key
	 * @param requestStr
	 * @param useKey
	 * @return
	 * @throws Exception
	 */
	public static Mono<String> toUserpost(String requestStr, RequestConfig requestConfig, CloseableHttpClient httpClient)
			throws Exception {

		HttpPost httpPost = new HttpPost(PAY_BASE_URL + PAY_TOUSER_PATH);
		httpPost.addHeader("Content-Type", "application/xml");
		StringEntity postEntity = new StringEntity(requestStr, "UTF-8");
		httpPost.setEntity(postEntity);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		String result = null;
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} finally {
			httpPost.abort();
		}
		return Mono.just(result);
	}
	
	
	/**
	 * 
	 * @param key      证书密码，默认为商户ID[MCHID]
	 * @param mchId    P12文件目录 证书路径 [指定读取证书格式为PKCS12]
	 * @param filepath 读取本机存放的PKCS12证书文件
	 * @return
	 */
	public static CloseableHttpClient initCert(String key, String filePath) throws Exception {
		KeyStore keyStore = KeyStore.getInstance(PKCS);
		FileInputStream instream = new FileInputStream(new File(filePath));
		try {
			keyStore.load(instream, key.toCharArray());
		} finally {
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, key.toCharArray()).build();
		SSLConnectionSocketFactory sslConnect = new SSLConnectionSocketFactory(sslcontext,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		return HttpClients.custom().setSSLSocketFactory(sslConnect).build();
	}

	/**
	 * 根据默认超时限制初始化requestConfig
	 * @return
	 */
	public static RequestConfig initRequestConfig() {
		return RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	}
}
