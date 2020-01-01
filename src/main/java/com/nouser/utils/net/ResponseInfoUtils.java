package com.nouser.utils.net;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 相应信息工具类
 */
public class ResponseInfoUtils {
    //返回值key
    public final static String CODE = "code";
    public final static String INFO = "info";

    //正确返回
    public final static Integer OK_200 = 200;
    public final static Integer OK_201 = 201;
    //信息错误提示
    public static final Integer REDIRECT_ERROR302 = 302;
    //异常错误提示
    public static final Integer Exception_ERROR500 = 500;
    public static final Integer ERROR404 = 404;

	/**
	 * 错误信息
	 * @param errorCode	return code
	 * @param context	return context
	 * @return	map
	 */
    public static Map<String, String> errorInfo(Integer errorCode, String context) {
        Map<String, String> result = new HashMap<>();
        result.put(CODE, ERROR404.toString());
        result.put(INFO, (context == null) ? "未知错误,请重试" : context);
        return result;
    }

	/**
	 * 正确信息
	 * 		code = 200;
	 * @param context	return context
	 * @return	Map
	 */
    public static Map<String, String> okInfo(String context) {
        Map<String, String> result = new HashMap<>();
        result.put(CODE, OK_200.toString());
        if (!StringUtils.isBlank(context)) {
            result.put(INFO, context);
        }
        return result;
    }

	/**
	 * 重定向Map
	 * 		code = 302;
	 * @param redirectUri	redirect uri
	 * @param context	return context
	 * @return	Map
	 */
    public static Map<String, String> redirectInfo(String redirectUri, String context) {
        Map<String, String> result = new HashMap<>();
        result.put(CODE, REDIRECT_ERROR302.toString());
        result.put("redirectUri", redirectUri);
        if (!StringUtils.isBlank(context)) {
            result.put(INFO, context);
        }
        return result;

    }

}
