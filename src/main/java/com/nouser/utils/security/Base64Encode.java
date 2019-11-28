package com.nouser.utils.security;

import java.util.Base64;

public class Base64Encode {

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return
	 */
	public static String encode(byte[] bstr) {
		return Base64.getEncoder().encodeToString(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		byte[] bs = Base64.getDecoder().decode(str);
		return new String(bs);
	}

	private Base64Encode() {
	}
}
