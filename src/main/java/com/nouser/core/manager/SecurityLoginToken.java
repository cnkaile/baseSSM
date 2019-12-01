package com.nouser.core.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.nouser.core.manager.impl.DefaultTokenManager;
import com.nouser.utils.RedisUtil;
import com.nouser.utils.UUIdUtil;

/**
 * @Title: SecurityTokenCheck.java
 * @Package com.nouser.core.manager
 * @author: zhoukl
 * @date: 2019年11月29日 上午11:19:33
 * @version V1.0
 */
@Component
public class SecurityLoginToken extends DefaultTokenManager {

	private static final String TOKEN_PREFIX = "logininfo:tokenSalt:";

	public String createLoginToken(String userId) {
		String salt = UUIdUtil.uuid16();
		int status = RedisUtil.setString(TOKEN_PREFIX + userId, salt);
		if (status == -1) {
			salt = "null";
		}
		return createToken(userId, salt);
	}

	public boolean checkLoginToken(String userId, String token) {
		if (StringUtils.isAnyBlank(userId, token)) {
			return false;
		}
		String salt = RedisUtil.getStr(userId);
		return checkToken(userId, salt, token);
	}
}
