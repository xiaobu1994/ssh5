/**
 * 
 */
package com.xiaobu.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类，用与日期处理
 * 
 * @author yaoxing
 * 
 *
 */
public class DateUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	/**
	 * 返回自定义格式的日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateFormatstr(Date date, String format) {
		String rstr = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			rstr = df.format(date);
		} catch (Exception e) {
			return "";
		}
		return rstr;
	}

	/**
	 * 获取当天日期
	 * 
	 * @return
	 */
	public static String getTodayYYYYMMDD() {
		return getDateToyyyy_MM_dd(new Date());
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTodayYYYYMMDDHHMMSS() {
		return getDateToyyyy_MM_dd_HH_mm_ss(new Date());
	}
	
	/**
	 * 返回yyyy-MM-dd:HH:mm:ss格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToyyyy_MM_dd_HH_mm_ss(Date date) {
		return getDateFormatstr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回yyyyMMdd格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToyyyyMMdd(Date date) {
		return getDateFormatstr(date, "yyyyMMdd");
	}

	/**
	 * 返回yyyy-MM-dd格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToyyyy_MM_dd(Date date) {
		return getDateFormatstr(date, "yyyy-MM-dd");
	}

	/**
	 * 返回HH:mm:ss格式的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToHH_mm_ss(Date date) {
		return getDateFormatstr(date, "HH:mm:ss");
	}

	/**
	 * 返回HH:mm格式的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToHH_mm(Date date) {
		return getDateFormatstr(date, "HH:mm");
	}

	/**
	 * 返回HHmmss格式的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToHHmmss(Date date) {
		return getDateFormatstr(date, "HHmmss");
	}

	/**
	 * 根据yyyy-MM-dd HH:mm:ss返回日期类型
	 * 
	 * @param dateStr
	 * @return Date 日期类型
	 */
	public static Date getYYYY_MM_dd_HH_mm_ssToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error(dateStr + ",yyyy-MM-dd HH:mm:ss转换成Date异常,异常信息是:", e);
		}
		return date;
	}
	
	/**
	 * 根据字符串日期返回"yyyy-MM-dd"日期类型(带有时分秒会自动忽略时分秒)
	 * 
	 * @param dateStr 
	 * @return Date 日期类型 时分秒均为00
	 */
	public static Date getYYYY_MM_ddToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error(dateStr + ",yyyy-MM-dd转换成Date异常,异常信息是:", e);
		}
		return date;
	}
	
	/**
	 * 日期互转 yyyy-MM-dd HH:mm:ss 转换为 HH:mm
	 * 
	 * @param dateStr
	 * @return String HH:mm
	 */
	public static String getDateStrToHH_mm(String dateStr) {
		Date date = getYYYY_MM_dd_HH_mm_ssToDate(dateStr);
		return getDateFormatstr(date, "HH:mm");
	}

	/**
	 * 时长处理方法 传入开日期，和时长，转换为结束日期 如：传入2015-12-12 13:23:21 加上分钟转换为 2015-12-12
	 * 14:23:21
	 * 
	 * @param startDate 开始日期
	 * @param time 时长
	 *            return 结束日期
	 */
	public static Date getEndDate(Date startDate, int time) {
		Date endDate = new Date(startDate.getTime() + time * 60 * 1000);
		return endDate;
	}

	/**
	 * 时长处理方法 传入开始日期，和时长，返回结束日期 如：传入2015-12-12 13:23:21 加上60分钟转换为 2015-12-12
	 * 14:23:21
	 * 
	 * @param startDateStr
	 *            开始日期字符串，格式：2015-12-12 13:23:21
	 * @param time
	 *            时长
	 * return String
	 *            结束日期字符串
	 */

	public static String getEndDateStr(String startDateStr, int time) {
		Date startDate = getYYYY_MM_dd_HH_mm_ssToDate(startDateStr);
		Date endDate = getEndDate(startDate, time);
		return getDateToyyyy_MM_dd_HH_mm_ss(endDate);
	}
	
	/**
	 * 判断当前时间是否在区间内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isCurrentTimeBetween(Date startTime, Date endTime) {
		long currentTime = System.currentTimeMillis();
		if (currentTime > startTime.getTime() && currentTime < endTime.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取总耗时
	 * 
	 * @param start
	 *            开时时间System.currentTimeMillis();
	 * @return
	 */
	public static long getTotalTime(long start) {
		return System.currentTimeMillis() - start;
	}

	/**
	 * by yaoxing
	 * 计算出离当前日期datas天的日期,若datas小于0表示当前日期之前datas天，若datas大于0表当前日期之后datas天
	 * 参数传值参考：30（后推30天）,-30（后推30天）
	 * 
	 * @param  datas 要计算的天数
	 * @return 得到日期
	 */
	public static Date getComputerDate(int datas) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, datas);
		String begin = new java.sql.Date(calendar.getTime().getTime()).toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		try {
			beginDate = sdf.parse(begin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginDate;
	}
	
	/**
	 * by yaoxing
	 * 计算出距离某个日期datas天的日期,若datas小于0表示当前日期之前datas天，若datas大于0表当前日期之后datas天
	 * 参数传值参考：30（后推30天）,-30（后推30天）
	 * 
	 * @param date 要计算的天数
	 * @return 得到日期
	 */
	public static Date getComputerDate(Date date,int datas) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, datas);
		return calendar.getTime();
	}
	
	/**
	 *  计算距离某个日期之后（之前）的月份的日期
	 *   参数传值参考：1（后推1个月）,-1（前推1个月）
	 * @param date 需要计算的日期
	 * @param datas 计算月数
	 * @return 得到的日期
	 */
	public static Date getComputerMonthDate(Date date,int datas) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.MONTH, datas);
		return getDateToYYYY_DD_MM(calendar.getTime());
	}
	
	/**
	 * by yaoxing
	 * 计算出离当前日期datas天的日期,若datas小于0表示当前日期之前datas天，若datas大于0表当前日期之后datas天
	 * 参数传值参考：30（后推30天）,-30（后推30天）
	 * 
	 * @param  datas 要计算的天数
	 * @return 得到日期字符串，如20140728
	 */
	public static String getComputeFormatDate(int datas) {
		Date date = getComputerDate(datas);
		String dateStr = getDateToyyyy_MM_dd(date);
		return dateStr + " 00:00:00";
	}

	/**
	 * 获取当天的时间
	 * 
	 * @return
	 */
	public static String getStartFormatToday() {
		return getTodayYYYYMMDD() + " 00:00:00";
	}

	/**
	 * 获取当天的时间
	 * 
	 * @return
	 */
	public static String getCurrentFromtDate() {
		return getDateToyyyy_MM_dd_HH_mm_ss(new Date());
	}

	/**
	 * 获取本周第一天的日期
	 * 
	 * @return yyyy_MM_dd
	 */
	public static String getWeekFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return (getDateToyyyy_MM_dd(cal.getTime()));
	}

	/**
	 * 获取本月第一天的日期
	 * 
	 * @return yyyy_MM_dd
	 */
	public static String getMonFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1); // 获取本月第一天的日期
		return (getDateToyyyy_MM_dd(cal.getTime()));
	}
	
	/**
	 * 获取本月最后一天的日期
	 * 
	 * @return yyyy_MM_dd
	 */
	public static String getMonLastDay() {
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return (getDateToyyyy_MM_dd(ca.getTime()));
	}

	/**
	 * 获取本年第一天的日期
	 * 
	 * @return yyyy_MM_dd
	 */
	public static String getYearFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1); // 获取本年第一天的日期
		return (getDateToyyyy_MM_dd(cal.getTime()));
	}

	/**
	 * 比较两个日期大小，date1大于date2,则返回true
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			return compareDate(dt1, dt2);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 比较两个日期大小，date1大于date2,则返回true
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 返回某个时间点多少天后的时间点
	 * 
	 * @param time morningEndTime
	 * @return yyyy-MM-dd hh:mm
	 */
	public static String getDayNewTime(String time, int day) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(time);
			long newTime = dt1.getTime() + 1000L*3600*24*day;
			Date newDate = new Date(newTime);
			return df.format(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * 计算天数
	 * @param startTime   开始日期
	 * @param endTime   结束日期
	 * @return
	 * @throws ParseException
	 */
    public static int getDayCount(String startTime,String endTime){  
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			Date smdate = sdf.parse(startTime);
			Date bdate = sdf.parse(endTime);
			Date date = new Date();
	    	
			if (bdate.getTime() > date.getTime()) {
				bdate = date;
	    	}
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24)+1;  
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return 0;
		}
    	
    } 
    
    /**
     * 字符串转日期格式
     * @param dateStr 日期字符串
     * @param formatStr 需要转换的格式
     * @return
     */
    public static Date getDateFromStr(String dateStr,String formatStr) {
        if(dateStr == null) {
            return null;
        }
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

	/**
	 * 将yyyy-MM-dd hh:mm:ss date格式的日期转换成 yyyy-MM-dd
	 * 清空时分秒
	 * @param date
	 * @return
	 */
	public static Date getDateToYYYY_DD_MM(Date date){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		// 将时分秒,毫秒域清零
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		return cal1.getTime();
	}
	/**
	 * 功能描述：根据日期返回小时
	 *
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 功能描述：根据日期返回小时
	 *
	 * @param dateStr 日期字符串含时分秒
	 * @return 返回小时
	 */
	public static int getHour(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getYYYY_MM_dd_HH_mm_ssToDate(dateStr));
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
     * 日期转"周日", "周一", "周二", "周三", "周四", "周五", "周六"
     * 
     * @param datetime 要转换的日期
     * @return
     */
	public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	//测试
	public static void main(String[] args) {
		Date computerMonthDate = getComputerMonthDate(new Date(), -6);
		
		System.out.println(getDateToyyyy_MM_dd_HH_mm_ss(computerMonthDate));
	}
	
}
