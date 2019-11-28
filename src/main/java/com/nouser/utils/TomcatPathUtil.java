package com.nouser.utils;


public class TomcatPathUtil {

	/**
	 * Tomcat根路径
	 * 例F:\tomcat\apache-tomcat-7.0.61
	 * @return
	 */
	public static String getTomcatPath(){
		return System.getProperty("catalina.home");
	}
	
	/**
	 * Tomcat classes路径
	 * 例：/F:/tomcat/apache-tomcat-7.0.61/webapps/mobile/WEB-INF/classes/
	 * @return
	 */
	public static String getTomcatClassPath(){
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}
}
