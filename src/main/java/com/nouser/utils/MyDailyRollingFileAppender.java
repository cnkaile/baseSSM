package com.nouser.utils;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 分级日志输出。
 * @author cuiwei
 */
public class MyDailyRollingFileAppender extends DailyRollingFileAppender {

	/**
	 * 判断日志等级。<p>
	 * 原为输出高于本文件的配置等级的日志。
	 * @param priority 日志等级
	 * @return true：与日志配置等级一致，false：与日志配置等级不一致
	 */
	@Override
	public boolean isAsSevereAsThreshold(Priority priority){
		return this.getThreshold() == null || this.getThreshold().equals(priority);
	}
}
