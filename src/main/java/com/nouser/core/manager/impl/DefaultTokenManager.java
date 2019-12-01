package com.nouser.core.manager.impl;

import org.apache.commons.lang3.StringUtils;

import com.nouser.core.manager.TokenManager;
import com.nouser.utils.MD5Util_LowerCase;

/**
 * 默认tokenManager
 * 
 * @Title: DefaultTokenManager.java
 * @Package com.nouser.core.manager.impl
 * @author: zhoukl
 * @date: 2019年11月29日 上午10:47:36
 * @version V1.0
 */
public class DefaultTokenManager implements TokenManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nouser.core.manager.TokenManager#createToken(java.lang.String, java.lang.String)
	 */
	@Override
	public String createToken(String origin, String salt) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("origin=").append(origin).append("&salt=").append(salt);
		return MD5Util_LowerCase.MD5Encode(sb.toString(), "utf-8");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nouser.core.manager.TokenManager#checkToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkToken(String origin, String salt, String token) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(token)) {
			return false;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("origin=").append(origin).append("&salt=").append(salt);
		return token.equals(MD5Util_LowerCase.MD5Encode(sb.toString(), "utf-8"));
	}

}
