package com.nouser.utils.file;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nouser.base.ProConstants;

/**
 * 
 * Properties工具类
 * 
 * @author qcodelv
 *
 */
public class PropertiesUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	public static Properties serverProperties = new Properties();

	private PropertiesUtil() {
	}

	static {
		init();
	}

	private static void init() {
		try {
			if (serverProperties == null)
				serverProperties = new Properties();
			serverProperties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(ProConstants.DEFALUT_PROPERTIES_FILE), "UTF-8"));
		} catch (Exception e) {
			logger.info("初始化配置文件出错了!!!!!");
		}
	}

	public static String getProperty(String str) {
		if (serverProperties == null) {
			serverProperties = new Properties();
			init();
		}
		String property = serverProperties.getProperty(str) == null ? ""
				: serverProperties.getProperty(str);
		return property;
	}

	public static void reinit() {
		try {
			serverProperties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(ProConstants.DEFALUT_PROPERTIES_FILE), "UTF-8"));
		} catch (Exception e) {
			logger.info("初始化配置文件出错了!!!!!");
		}
	}
}
