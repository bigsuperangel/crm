package com.crm.common.utils;

import hirondelle.date4j.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf_date = new SimpleDateFormat(
			"yyyyMMdd");
	private static final SimpleDateFormat sdf_date2 = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	public static final String DATE_FORMAT_DAY_DATE4J = "YYYY-MM-DD";  //date4j格式
	public static final String DATE_FORMAT_DAYTIME_DATE4J = "YYYY-MM-DD hh:mm:ss"; //date4j格式
	public static final String DATE_FORMAT_MONTH_DATE4J = "YYYY-MM";//date4j格式
	public static final String DATE_FORMAT_LONGTIME_DATE4J_TIME = "YYYYMMDDhhmmsssss";//date4j格式
	
	/**
	 * 获取释放时间
	 * @param days
	 * @return
	 */
	public static String getFreeTime(int days){
		DateTime now = DateTime.today(TimeZone.getDefault());
		DateTime freeDay = now.plusDays(days);
		return freeDay.format(DATE_FORMAT_DAY_DATE4J);
	}
	
	/**
	 * 把时间格式化成字符串
	 * @param format
	 * @return
	 */
	public static String now2String(String format){
		return DateTime.now(TimeZone.getDefault()).format(format);
	}
	
	/**
	 * 默认当天时间
	 * YYYY-MM-DD hh:mm:ss
	 * @return
	 */
	public static String now2String(){
		return now2String(DATE_FORMAT_DAYTIME_DATE4J);
	}
	
	/**
	 * 日期格式化成字符串
	 * @param format
	 * @return
	 */
	public static String today2String(String format){
		return DateTime.today(TimeZone.getDefault()).format(format);
	}
	
	/**
	 * 默认当天日期字符串
	 * YYYY-MM-DD
	 * @return
	 */
	public static String today2String(){
		return today2String(DATE_FORMAT_DAY_DATE4J);
	}

	public static String getFormatedCurrentDate() {
		return getFormattedDate(new Date());
	}

	public static String getFormattedDate(Date date) {
		return sdf.format(date);
	}

	/**
	 * 获取几天后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getFormatedFixedDate(Date date, int days) {
		date = getFixedDate(date, days);
		return sdf_date2.format(date);
	}
	
	public static Date getDateFromStr(String dateString){
		try {
			return sdf_date2.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取几天后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getFormatedFixedDate(int days) {
		return getFormatedFixedDate(new Date(), days);
	}
	
	
	public static void main(String[] args){
		System.out.println(getFreeTime(90));
	}

	public static String getFormatedCurrentDate2() {
		return sdf_date.format(new Date());
	}

	public static Date getFixedDate(Date oriDate, int days) {
		long current = oriDate.getTime();
		current = current + days * 1000 * 60 * 60 * 24;
		Date date = new Date(current);
		return date;
	}
}
