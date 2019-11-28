package com.nouser.utils.net;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 简单post请求和get请求
 * 
 * @Title: HttpClient.java
 * @Package com.nouser.utils.net
 * @author: zhoukl
 * @date: 2019年11月28日 下午1:11:21
 * @version V1.0
 */
public class HttpClient {
	private static final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();

	/**
	 * http Get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String responseContent = null;
		try {
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			HttpResponse response = (HttpResponse) httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}

	/**
	 * http post 请求
	 * 
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public static String httpPost(String url, JSONObject jsonParam) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (null != jsonParam) {
				StringEntity entity = new StringEntity(jsonParam.toString(), ContentType.create("application/json", Consts.UTF_8));
				entity.setContentEncoding("utf-8");
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "utf-8");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * http post 请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPost(String url, String params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String content = null;
		try {
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			if (!StringUtils.isBlank(params)) {
				StringEntity postingString = new StringEntity(params, ContentType.create("application/json", Consts.UTF_8));
				postingString.setContentEncoding("utf-8");
				post.setEntity(postingString);
			}
			HttpResponse response = httpClient.execute(post);
			if (response != null) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity, "utf-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	private HttpClient() {
		super();
	}
}
