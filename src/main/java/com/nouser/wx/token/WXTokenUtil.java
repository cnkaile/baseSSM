package com.nouser.wx.token;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nouser.utils.net.HttpClient;
import com.nouser.wx.WechatConfig;

/**
 * 
 * 微信公众号获取Token的工具类
 * 
 * @author qcodelv
 *
 */
public class WXTokenUtil {

	private static final Logger logger = LoggerFactory.getLogger(WXTokenUtil.class);
	
	/**
	 * 获取微信的AccessToken
	 * 
	 * @return
	 */
	public static String getWXToken() {
		String tokenJSON = getWXTokenJson(WechatConfig.APP_ID, WechatConfig.APP_SECRET);
		String token = getWXTokenStr(tokenJSON);
		if (StringUtils.isEmpty(token)) {
			logger.error("token获取错误：" + tokenJSON);
		}
		return token;
	}
	
	public static Map<String, Object>  getWXTokenMap() {
		String tokenJSON = getWXTokenJson(WechatConfig.APP_ID, WechatConfig.APP_SECRET);
		if (StringUtils.isNotEmpty(tokenJSON)) {
			Map<String, Object> map = JSON.parseObject(tokenJSON);
			return map;
		}
		return null;
	}

	/**
	 * 获取微信的AccessToken
	 * 
	 * @return {"access_token":"16_Da.....","expires_in":7200}
	 */
	private static String getWXTokenJson(String APPID, String APPSECRET) {
		StringBuilder sbUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
		sbUrl.append("&appid=").append(APPID).append("&secret=").append(APPSECRET);
		String tokenJSON = null;
		try {
			tokenJSON = HttpClient.httpGet(sbUrl.toString());
		} catch (Exception e) {
			logger.error("getToken:"+tokenJSON,e);
		}
		return tokenJSON;
	}

	/**
	 * 根据获取的json
	 * 
	 * @param tokenJSON
	 *            {"access_token":"16_Da.....","expires_in":7200}
	 * @return 16_Da
	 */
	private static String getWXTokenStr(String tokenJSON) {
		JSONObject object = null;
		if (tokenJSON != null) {
			object = JSON.parseObject(tokenJSON);
		} else {
			return null;
		}
		String access_token = null;
		if (object != null) {
			access_token = object.getString("access_token");
		} else {
			return null;
		}
		return access_token;
	}
}
