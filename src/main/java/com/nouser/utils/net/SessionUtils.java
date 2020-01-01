package com.nouser.utils.net;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
 * 从Session总取值
 * @ClassName: SessionUtils 
 * @Description: TODO
 * @author: zhoukl
 * @date: 2019年11月14日 上午11:18:04
 */
public class SessionUtils {

	public static <T> T getSessionClass(HttpServletRequest request, String key, Class<T> clazz) {
		Object obj = request.getSession().getAttribute(key);
		if(obj == null) {
			return null;
		}else if(obj instanceof String) {
			String sessionStr = (String)obj;
			return JSON.parseObject(sessionStr, clazz);
		}
//		else if(clazz.isAssignableFrom(obj.getClass())) {
//			return (T)obj;
//		}
		return null;
	}
	
	public static void setSessionValue(HttpServletRequest request, String key, Object value) {
		if(value instanceof String) {
			request.getSession().setAttribute(key, value);
		}else {
			request.getSession().setAttribute(key, JSON.toJSONString(value));
		}
	}

	private SessionUtils() {
		super();
	}
	
}
