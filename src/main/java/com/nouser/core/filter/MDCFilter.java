package com.nouser.core.filter;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;

import com.nouser.utils.IpAddressUtil;
import com.nouser.utils.UUIdUtil;

/**
 * MDC过滤器
 * 
 * @Title: MDCFilter.java
 * @Package com.nouser.filter
 * @author: zhoukl
 * @date: 2019年11月26日 上午11:30:18
 * @version V1.0
 */
public class MDCFilter implements Filter {
	// MDC填充的KEY
	// 请求流水号
	public static final String REQUEST_UUID = "REQUEST_UUID";
	// 服务器ip
	public static final String HOST_IP = "HOST_IP";
	// 服务器名称
	public static final String HOST_NAME = "HOST_NAME";
	// 应用名称
	public static final String APPKEY = "APPKEY";
	// 用户id
	public static final String USER_ID = "USER_ID";
	// 用户id
	public static final String MEMBER_ID = "MEMBER_ID";
	// 访问ip
	public static final String REMOTE_IP = "REMOTE_IP";

	public static final String CODE_STRING = "CODE_STRING";

	private String appkey = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		appkey = filterConfig.getInitParameter("appkey");// appkey可以在filter
															// init-param中配置
		if (appkey == null) {
			appkey = "";
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			insertIntoMDC(request);// 填充MDC
			chain.doFilter(request, response);
		} finally {
			// 请求结束，注意清除MDC中的内容，否则会造成内存泄露问题
			MDC.clear();
		}

	}

	/**
	 * mdc添加
	 * 
	 * @param request
	 */
	protected void insertIntoMDC(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		try {
			MDC.put(MDCFilter.REQUEST_UUID, UUIdUtil.uuid());
			InetAddress localHostLANAddress = IpAddressUtil.getLocalHostLANAddress();
			if (localHostLANAddress != null) {
				MDC.put(MDCFilter.HOST_IP, localHostLANAddress.getHostAddress());
			}
			MDC.put(MDCFilter.APPKEY, appkey);
			MDC.put(MDCFilter.USER_ID, request.getParameter("userId") != null ? request.getParameter("userId") : "");
			MDC.put(MDCFilter.MEMBER_ID, request.getParameter("memberId") != null ? request.getParameter("memberId") : "");
			MDC.put(MDCFilter.REMOTE_IP, IpAddressUtil.getIpAddress(req));

			String codeString = req.getHeader("codeString");
			if (StringUtils.isNotEmpty(codeString)) {
				MDC.put(MDCFilter.CODE_STRING, codeString);
			} else {
				MDC.put(MDCFilter.CODE_STRING, request.getParameter("codeString") != null ? request.getParameter("codeString") : "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// destroy
	}
}
