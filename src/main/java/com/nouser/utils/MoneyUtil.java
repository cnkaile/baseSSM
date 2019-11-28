package com.nouser.utils;

import java.math.BigDecimal;

/**
 * 
 * @author qcodelv
 *
 */
public class MoneyUtil {

	/*
	 * 元转分
	 */
	public static int YuanToCent(BigDecimal yuan) {
		yuan = yuan.multiply(new BigDecimal("100"));
		int cent = yuan.intValue();
		return cent;
	}

	/**
	 * 分转元
	 * 
	 * @param cent
	 * @return
	 */
	public static BigDecimal CentToYuan(int cent) {
		BigDecimal yuan = BigDecimal.valueOf(cent);
		yuan = yuan.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
		return yuan;
	}

	public static boolean isBigThan(BigDecimal yuan, BigDecimal yuan0) {
		if (yuan.compareTo(yuan0) == -1) {
			return false;
		} else if (yuan.compareTo(yuan0) == 0) {
			return false;
		} else if (yuan.compareTo(yuan0) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isLittleThan(BigDecimal yuan, BigDecimal yuan0) {
		if (yuan.compareTo(yuan0) == -1) {
			return true;
		} else if (yuan.compareTo(yuan0) == 0) {
			return false;
		} else if (yuan.compareTo(yuan0) == 1) {
			return false;
		} else {
			return false;
		}
	}
}
