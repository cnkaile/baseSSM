package com.nouser.utils.string;

import org.apache.commons.lang3.StringUtils;

/**
 * 过滤emoji字符
 * 
 * @Title: EmojiUtil.java
 * @Package com.nouser.utils.string
 * @author: zhoukl
 * @date: 2019年11月27日 下午3:43:20
 * @version V1.0
 */
public class EmojiUtil {

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		int len = source.length();
		StringBuilder buf = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				buf.append(codePoint);
			} else {
				buf.append("*");
			}
		}
		return buf.toString();
	}

	private EmojiUtil() {
		super();
	}
}
