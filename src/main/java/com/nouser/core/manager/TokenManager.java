package com.nouser.core.manager;

/**
 * 校验TOKEN
 * 
 * @Title: TokenManager.java
 * @Package com.nouser.core.manager
 * @author: zhoukl
 * @date: 2019年11月29日 上午10:33:06
 * @version V1.0
 */
public interface TokenManager {
	/**
	 * 创建token
	 * 
	 * @param origin
	 *            源标识
	 * @param salt
	 *            盐
	 * @return token
	 */
	public String createToken(String origin, String salt);

	/**
	 * 校验token
	 * 
	 * @param origin
	 *            源标识
	 * @param salt
	 *            盐
	 * @param token
	 *            token
	 * @return 是否匹配
	 */
	public boolean checkToken(String origin, String salt, String token);
}
