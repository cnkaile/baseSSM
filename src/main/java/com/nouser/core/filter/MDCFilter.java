package com.nouser.core.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * log4j模型
 * 
 * @Title: MDCFilter.java
 * @Package com.nouser.core.filter
 * @author: zhoukl
 * @date: 2019年11月26日 上午11:29:42
 * @version V1.0
 */
public class MDCFilter extends BaseMDCFilter {
	// MDC填充的KEY

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void destroy() {
		// destroy
	}
}
