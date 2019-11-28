package com.nouser.utils.net;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * cookie工具类
 * 
 * @Title: CookieUtils.java
 * @Package com.nouser.utils.net
 * @author: zhoukl
 * @date: 2019年11月27日 下午2:16:28
 * @version V1.0
 */
public class CookieUtils {
	private static final int COOKIE_MAXAGE = 60 * 60 * 24;

	/**
	 * cookie 转 Map
	 * 
	 * @param req
	 *            request
	 * @return
	 */
	public static Map<String, String> cookie2Map(HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		if (null == req) {
			throw new RuntimeException("req is null");
		}
		Cookie[] cookies = req.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				result.put(cookie.getName(), cookie.getValue());
			}
		}
		return result;
	}

	/**
	 * 从cookie区值
	 * 
	 * @param name
	 *            key
	 * @param request
	 * @param author
	 *            可为null
	 * @return V
	 */
	public static String getValue4Name(String name, HttpServletRequest request, String author) {
		String result = null;
		if (StringUtils.isBlank(name)) {
			return result;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1) {
			return result;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				result = cookie.getValue();
				break;
			}
		}
		return result;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void writeCookies(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(COOKIE_MAXAGE);
		response.addCookie(cookie);
	}

	private CookieUtils() {
	}
}
