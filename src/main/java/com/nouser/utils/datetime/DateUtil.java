package com.nouser.utils.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	/**
	 * 时间格式化字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (date == null)
			return null;
		else if (StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd hh:mm:ss";
		}
		SimpleDateFormat sdFormat = new SimpleDateFormat(format, Locale.getDefault());
		String str_Date = sdFormat.format(date);
		return str_Date;
	}

	/**
	 * 字符串转换为时间类型
	 * 
	 * @param str
	 *            要格式化字符串
	 * @param formatStr
	 *            字符串的日期格式
	 * @return
	 */
	public static Date strToDate(String str, String formatStr) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (StringUtils.isBlank(formatStr)) {
			formatStr = "yyyy-MM-dd hh:mm:ss";
		}
		SimpleDateFormat defaultDate = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = defaultDate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param yearNum
	 * @param monthNum
	 * @param dateNum
	 * @param hourNum
	 * @param minuteNum
	 * @param secondNum
	 * @return
	 */
	public static Date calDate(Date date, int yearNum, int monthNum, int dateNum, int hourNum, int minuteNum, int secondNum) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, monthNum);
		cal.add(Calendar.YEAR, yearNum);
		cal.add(Calendar.DATE, dateNum);
		cal.add(Calendar.HOUR_OF_DAY, hourNum);
		cal.add(Calendar.MINUTE, minuteNum);
		cal.add(Calendar.SECOND, secondNum);
		result = cal.getTime();
		return result;
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param yearNum
	 * @param monthNum
	 * @param dateNum
	 * @throws Exception
	 */
	public static String calDate(String date, int yearNum, int monthNum, int dateNum) throws Exception {
		String result = "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sd.parse(date));
		cal.add(Calendar.MONTH, monthNum);
		cal.add(Calendar.YEAR, yearNum);
		cal.add(Calendar.DATE, dateNum);
		result = sd.format(cal.getTime());
		return result;
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param yearNum
	 * @param monthNum
	 * @param dateNum
	 * @return
	 */
	public static Date calDate(Date date, int yearNum, int monthNum, int dateNum) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, monthNum);
		cal.add(Calendar.YEAR, yearNum);
		cal.add(Calendar.DATE, dateNum);
		result = cal.getTime();
		return result;
	}

	/**
	 * 日期分钟计算
	 * 
	 * @param date
	 * @param minNum
	 * @return
	 */
	public static Date calDateMin(Date date, int minNum) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minNum);
		result = cal.getTime();
		return result;
	}

	/**
	 * 日期小时计算
	 * 
	 * @param date
	 * @param hourNum
	 * @return
	 */
	public static Date calDateHour(Date date, int hourNum) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hourNum);
		result = cal.getTime();
		return result;
	}

	private DateUtil() {
		super();
	}
}
