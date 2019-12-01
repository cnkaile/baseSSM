package com.nouser.core.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nouser.base.ProConstants;
import com.nouser.core.annotation.IgnoreSecurity;
import com.nouser.core.manager.SecurityLoginToken;

/**
 * @Title: SecurityLoginInterceptor.java
 * @Package com.nouser.core.interceptor
 * @author: zhoukl
 * @date: 2019年11月29日 下午3:40:34
 * @version V1.0
 */
public class SecurityLoginInterceptor implements HandlerInterceptor {

	@Resource
	private SecurityLoginToken securityLoginToken;
	@Value(value = "XL-Token")
	private String tokenName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			Method method = ((HandlerMethod) handler).getMethod();
			if (method.isAnnotationPresent(IgnoreSecurity.class)) {
				return true;
			}
			String token = request.getHeader(tokenName == null ? "XL-Token" : tokenName);
			String userId = request.getParameter(ProConstants.REQUEST_USER_SIGN);
			if (securityLoginToken.checkLoginToken(userId, token)) {
				return true;
			}
			// 设置转发
			request.getRequestDispatcher("").forward(request, response);
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
