/**
 * @Title:  AjaxCrossDomain.java
 * @Package	com.nouser.core.filter
 * @author:	zhoukl
 * @date:	2019年11月29日 上午9:51:25
 * @version	V1.0
 */
package com.nouser.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 解决ajax跨域问题,设置允许域名跨域
 * Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制。
 * Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS。
 * Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
 * Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type。
 * Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割
 * 
 * 
 * @Title: AjaxCrossDomainFilter.java
 * @Package com.nouser.core.filter
 * @author: zhoukl
 * @date: 2019年11月29日 上午9:53:03
 * @version V1.0
 */
public class AjaxCrossDomainFilter implements Filter {

	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.allowOrigin = filterConfig.getInitParameter("allowOrigin");
		this.allowMethods = filterConfig.getInitParameter("allowMethods");
		this.allowCredentials = filterConfig.getInitParameter("allowCredentials");
		this.allowHeaders = filterConfig.getInitParameter("allowHeaders");
		this.exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (request.getClass().isAssignableFrom(HttpServletRequest.class) && response.getClass().isAssignableFrom(HttpServletResponse.class)) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			if (!StringUtils.isBlank(allowOrigin)) {
				String currentOrigin = req.getHeader("Origin");
				if (currentOrigin.matches(allowOrigin)) {
					resp.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}else {
					
				}
			}
			if (!StringUtils.isBlank(allowMethods)) {
				resp.setHeader("Access-Control-Allow-Methods", allowMethods);
			}
			if (!StringUtils.isBlank(allowCredentials)) {
				resp.setHeader("Access-Control-Allow-Credentials", allowCredentials);
			}
			if (!StringUtils.isBlank(allowHeaders)) {
				resp.setHeader("Access-Control-Allow-Headers", allowHeaders);
			}
			if (!StringUtils.isBlank(exposeHeaders)) {
				resp.setHeader("Access-Control-Expose-Headers", exposeHeaders);
			}
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
