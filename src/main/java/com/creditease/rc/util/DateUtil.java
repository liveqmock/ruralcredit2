package com.creditease.rc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Title: 日期工具类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-8-9
 * @author xiebingfeng
 * @version v1.0
 */
public class DateUtil {

	/**
	 * 查询从今天开始计算的第二天和第二个工作日的日期
	 * @return List<Date>
	 */
	public static List<Date> getTodayNextDayAndNextWeekday(){
		return getNextDayAndNextWeekday(new Date());
	}
	
	/**
	 * 查询第二天和第二个工作日的日期
	 * @param date 起始日期
	 * @return List<Date>
	 */
	public static List<Date> getNextDayAndNextWeekday(Date date){
		List<Date> list = new LinkedList<Date>();
		list.add(getNextDay(date));
		list.add(getNextWeekday(date));
		return list;
	}
	
	/**
	 * 查询第二天的日期
	 * @param date 起始日期
	 * @return Date
	 */
	public static Date getNextDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day+1);
		return calendar.getTime();
	}
	/**
	 * 查询前一天的日期
	 * @param date 起始日期
	 * @return Date
	 */
	public static Date getYesterday(Date date){
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DATE,   -1);
	    String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	 //System.out.println(yesterday);
	    return cal.getTime();
	}
	
	/**
	 * 查询第二个工作日的日期
	 * @param date 起始日期
	 * @return Date
	 */
	public static Date getNextWeekday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = 0;
		do {
			calendar.set(Calendar.DAY_OF_MONTH, ++day);
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		} while(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY );
		return calendar.getTime();
	}
	
	private static Date getNextWeekdayTest(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = 0;
		do {
			calendar.add(Calendar.DATE, 1);
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		} while(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY );
		return calendar.getTime();
		}
	/**
	 * 获取date 后几日的工作时间
	 * @param date 当前日期
	 * @param days 几日
	 * @author manzhang
	 * @return
	 */
	public static Date getWeekDay(Date date,int days){
		if(days == 0){
			return date;
		}else{
			date =getNextWeekdayTest(date);
			days--;
			return getWeekDay(date,days);
		}
	};
	/**
	 * 根据参数生成还款计划的日期列表
	 * @param start 起始日期
	 * @param time 生成多少个还款日
	 * @param step 二次之间间隔几个月
	 * @return List<Date>
	 */
	public static List<Date> buildReturnDates(Date start, int time, int step){
		List<Date> list = new LinkedList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int maxday = 0;
		for (int i = 0; i < time; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.YEAR, year);
			month += step;
			calendar.set(Calendar.MONTH, month);
			maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if(day > maxday){
				calendar.set(Calendar.DAY_OF_MONTH, maxday);
			} else {
				calendar.set(Calendar.DAY_OF_MONTH, day);
			}
			list.add(calendar.getTime());
		}
		return list;
	}
	
	/**
	 * 生成还款计划的日期列表
	 * @param start 起始日期
	 * @param time 生成多少个还款日
	 * @return List<Date>
	 */
	public static List<Date> buildReturnDates(Date start, int time){
		return buildReturnDates(start, time, 1);
	}
	
	/**
	 * 日期转换成字符串
	 * @param date 日期
	 * @return String
	 */
	public static String dateConvertString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 用pattern指定的格式格式date
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return String
	 */
	public static String dateConvertString(Date date,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 转换日期格式
	 * @param dateStr 日期格式
	 * @return Date
	 */
	public static Date stringConvertDate(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转化字符串日期为Date
	 * @param dateStr 日期字符串
	 * @param pattern 日期格式
	 * @return Date
	 */
	public static Date stringConvertDate(String dateStr,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间字符串转化成日期
	 * @param dateStr 日期字符串
	 * @return Date
	 */
	public static Date stringConvertDateTime(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转化date日期为字符串
	 * @param date 日期
	 * @return String
	 */
	public static String dateConvertStringTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日期转换成yyyy-mm-dd日期
	 * @param date
	 * @return 
	 * @version v1.0 
	 * 2013-6-21
	 */
	public static Date dateConvertDate(Date date){
		try{
			
			String dateStr =dateConvertString(date);
			return stringConvertDate(dateStr);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日期转换成yyyy-MM-dd hh24:mm：ss日期
	 * @param date
	 * @return 
	 * @version v1.0 
	 * 2013-6-21
	 */
	public static Date dateConvertDateTime(Date date){
		try{
			
			String dateStr =dateConvertStringTime(date);
			return stringConvertDateTime(dateStr);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  时间戳装成stringTime
	 * @param timestamp
	 * @return 
	 * @version v1.0 
	 * 2013-6-21
	 */
	public static String  timestampConvertStringTime(String timestamp){
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (timestamp == null || "".equals(timestamp)) {
			timestamp = dateConvertStringTime(new Date());
		}
		Long time = new Long(timestamp);
		return format.format(time);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  时间戳转化成dateTime
	 * @param timestamp
	 * @return 
	 * @version v1.0 
	 * 2013-6-21
	 */
	public static Date timestampConvertDateTime(String timestamp){
		String time=timestampConvertStringTime(timestamp);
		return stringConvertDateTime(time);

	}
	
	/**
	 * 
	 *@author 韩大年
	 *@function： 返回endday与startday间隔天数
	 *@param startday 
	 *@param endday 
	 *@return int
	 *2012-2-27
	 */
	 public static int getBetweenDays(String startday,String endday){         
		 int betweenDays = 0;
		 Date d1 = stringConvertDate(startday);
		 Date d2 = stringConvertDate(endday);
		 Calendar c1 = Calendar.getInstance();
		 Calendar c2 = Calendar.getInstance();
		 c1.setTime(d1);
		 c2.setTime(d2);
		 // 保证第二个时间一定大于第一个时间
		 if(c1.after(c2)){
			 c1 = c2;
			 c2.setTime(d1);
		 }
		 int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		 betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
		 for(int i=0;i<betweenYears;i++){
			 c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));
			 betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		 }
		 return betweenDays;  
	}  
	 
	/**
	 * 年度所有周末
	 * @param year 年
	 * @return List<String>
	 */
	public static List<String> getWeekends(String year){
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Integer.valueOf(year), 0, 1);
		for(int day = 1; day <= cal.getActualMaximum(Calendar.DAY_OF_YEAR); day++){
			cal.set(Calendar.DAY_OF_YEAR, day);
			int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			if(weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY){
				list.add(sdf.format(cal.getTime()));
			}
		}
		return list;
	}
	
	/**
	 * 判断日期是否为周六周日
	 * @param date 日期 
	 * @return boolean
	 * @throws Exception 异常
	 */
	public static boolean isWeekend(String date) throws Exception{
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date tarDate = sdfInput.parse(date);
		calendar.setTime(tarDate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek==1 ||dayOfWeek==7){
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 日期转日历
	 * @param date 日期
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar getGregorianCalendar(Date date){
		
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		
		int year =cal.get(Calendar.YEAR);
		int month =cal.get(Calendar.MONTH);
        int dayOfMonth=cal.get(Calendar.DAY_OF_MONTH);
        int hourOfDay=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int second=cal.get(Calendar.SECOND);
		
		GregorianCalendar gregorianCalendar= new GregorianCalendar(year,month,dayOfMonth,hourOfDay,minute,second);
		return  gregorianCalendar;
		
	}
	
	/**
	 * 测试方法
	 * @param args 参数
	 */
	public static void main(String[] args) {
		// 测试生成还款计划的日期列表
//		List<Date> list = buildReturnDates(new Date(2011-1900,7,31), 12); //测试从2011年8月31日生成还款日
////		List<Date> list = buildReturnDates(new Date(2011-1900,7,1), 12); //测试从2011年8月1日生成还款日
//		for (Date date : list) {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println(format.format(date));
//		}
//		
		
		// 测试查询第二天和第二个工作日的日期
		List<Date> list = getNextDayAndNextWeekday(new Date());
//		List<Date> list = getNextDayAndNextWeekday(new Date(2011-1900,7,12));
//		List<Date> list = getNextDayAndNextWeekday(new Date(2011-1900,7,31));
//		List<Date> list = getNextDayAndNextWeekday(new Date(2011-1900,6,29));
//		for (Date date : list) {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println(format.format(date));
//		}
		System.out.println(dateConvertStringTime(new Date()));
	}
	
}
