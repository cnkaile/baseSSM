/**
 * @Title:  ExceptionAdvice.java
 * @Package	com.nouser.advice
 * @author:	zhoukl
 * @date:	2019年11月28日 下午5:25:55
 * @version	V1.0
 */
package com.nouser.advice;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nouser.utils.net.RespInfoUtils;

/**
 * 异常处理方法
 * 在运行时从上往下依次调用每个异常处理方法，匹配当前异常类型是否与@ExceptionHandler注解所定义的异常相匹配，若匹配，则执行该方法，同时忽略后续所有的异常处理方法，最终会返回经JSON序列化后的Response对象。
 * 
 * @org.springframework.web.bind.annotation.ResponseStatus 注解定义响应状态码
 * @org.springframework.web.bind.annotation.ExceptionHandler 注解定义具体需要拦截的异常类
 * @Title: ExceptionAdvice.java
 * @Package com.nouser.advice
 * @author: zhoukl
 * @date: 2019年11月28日 下午5:25:55
 * @version V1.0
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        logger.error("参数解析失败", e);
		return RespInfoUtils.errorInfo(RespInfoUtils.Exception_ERROR500, "参数解析错误");
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String, Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        logger.error("不支持当前请求方法", e);
		return RespInfoUtils.errorInfo(RespInfoUtils.Exception_ERROR500, "参数解析错误");
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Map<String, Object> handleHttpMediaTypeNotSupportedException(Exception e) {
//        logger.error("不支持当前媒体类型", e);
		return RespInfoUtils.errorInfo(RespInfoUtils.Exception_ERROR500, "参数解析错误");
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> handleException(Exception e) {
//        logger.error("服务运行异常", e);
		return RespInfoUtils.errorInfo(RespInfoUtils.Exception_ERROR500, "参数解析错误");
	}
}
