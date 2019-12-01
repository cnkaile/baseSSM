package com.nouser.core.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;

import com.nouser.core.annotation.IgnoreSecurity;
import com.nouser.core.manager.SecurityLoginToken;

/**
 * @Title:  SecurityLoginAspect.java
 * @Package	com.nouser.core.aspect
 * @author:	zhoukl
 * @date:	2019年11月29日 上午11:33:56
 * @version	V1.0
 */
@Deprecated
public class SecurityLoginAspect {
	private static final String LOGIN_TOKEN_NAME = "XL-Token";
	
	@Resource
	private SecurityLoginToken securityLoginToken; 
	@Value(value = "XL-Token")
	private String tokenName;
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void allRequest() {}
	
	@Around("allRequest()")
	public Object around1(ProceedingJoinPoint pjp) throws Throwable  {
		Signature signature = pjp.getSignature();
		if(signature instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			if(method.isAnnotationPresent(IgnoreSecurity.class)) {
				return pjp.proceed();
			}
		}
		return null;
	}
	
}
