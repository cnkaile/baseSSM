package com.nouser.utils.net;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断请求类型
 * 
 * @Title: AjaxRequestUtils.java
 * @Package com.nouser.utils.net
 * @author: zhoukl
 * @date: 2019年11月26日 下午4:46:25
 * @version V1.0
 */
public class RequestTypeUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

	private RequestTypeUtils() {
	}
}
