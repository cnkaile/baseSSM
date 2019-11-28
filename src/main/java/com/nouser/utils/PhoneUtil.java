package com.nouser.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 验证手机号
 * 
 * @author
 *
 */
public class PhoneUtil {

	// 手机号码
	public static final Pattern PHONE_NUMBER = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");

	/**
	 * 验证手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNum(String phone) {
		if (phone==null||phone.length()==0) {
			return false;
		}
		boolean matches = PHONE_NUMBER.matcher(phone.trim()).matches();
		return matches;
	}
	
	/**
	 * 生成随机的验证码
	 * @return
	 */
	public static int productCode() {
		Random rand = new Random();
		return rand.nextInt(9000) + 1000;
	}

}
