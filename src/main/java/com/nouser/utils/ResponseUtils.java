package com.nouser.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ResponseUtils {
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
	
	public static Map<String, Object> errorInfo(Integer errorCode, String context){
		Map<String, Object> result = new HashMap<>();
		if(errorCode == ERROR404) {
			result.put(CODE, ERROR404);
			result.put(INFO, (context == null) ? "未知错误,请重试" : context);
		} 
		else if(errorCode == Exception_ERROR500) {
			result.put(CODE, Exception_ERROR500);
			result.put(INFO, (context == null) ? "未知错误,请重试" : context);
		}
		
		return result;
	}
	
	public static Map<String, Object> okInfo(Integer code, String context){
		Map<String, Object> result = new HashMap<>();
		result.put(CODE, code);
		if(!StringUtils.isBlank(context)) {
			result.put(INFO, context);
		}
		return result;
	}
	
	public static Map<String, Object> okInfo(String context){
		Map<String, Object> result = new HashMap<>();
		result.put(CODE, OK_200);
		if(!StringUtils.isBlank(context)) {
			result.put(INFO, context);
		}
		return result;
	}
	
	public static Map<String, Object> redirectInfo(String skipURI, String context){
		Map<String, Object> result = new HashMap<>();
		result.put(CODE, REDIRECT_ERROR302);
		result.put("skipURI", skipURI);
		if(!StringUtils.isBlank(context)) {
			result.put(INFO, context);
		}
		return result;
		
	}
	
}
