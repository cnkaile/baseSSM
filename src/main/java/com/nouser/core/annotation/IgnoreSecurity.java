package com.nouser.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * @Title:  IgnoreSecurity.java
 * @Package	com.nouser.core.annotation
 * @author:	zhoukl
 * @date:	2019年11月29日 下午12:04:25
 * @version	V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {

}
