package com.arjjs.ccm.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用于获取当前的
 * 
 * @author arj
 *
 */
public class TimeScope {
	// private Date weekBegin;
	// private Date weekEnd;
	// private Date monthBegin;
	// private Date monthEnd;

	private Date dateFrist;
	private Date dateSecond;

	public static Date getWeekBegin() {
		return getWeekScope().getDateFrist();
	}

	public static Date getWeekEnd() {
		return getWeekScope().getDateSecond();
	}

	public static Date getMonthBegin() {
		return getMonthScope().getDateFrist();
	}

	public static Date getMonthEnd() {
		return getMonthScope().getDateSecond();
	}

	public Date getDateFrist() {
		return dateFrist;
	}

	public void setDateFrist(Date dateFrist) {
		this.dateFrist = dateFrist;
	}

	public Date getDateSecond() {
		return dateSecond;
	}

	public void setDateSecond(Date dateSecond) {
		this.dateSecond = dateSecond;
	}

	/**
	 * 获取指定日期的当月的开始时间与结束时间
	 * 
	 * @return 当月的开始-结束 日期
	 */
	public static TimeScope getMonthScope() {
		// 返回的 时间范围对象
		TimeScope returnMonth = new TimeScope();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 每月的第一天时间
		returnMonth.setDateFrist(cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		// 每月的最后一天
		returnMonth.setDateSecond(cal.getTime());
		return returnMonth;
	}

	/**
	 * 每周的第一天日期与最后一天日期
	 * 
	 * @return
	 */
	public static TimeScope getWeekScope() {
		TimeScope returnTime = new TimeScope();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		returnTime.setDateFrist(cal.getTime());
		// 所在周开始日期
		cal.add(Calendar.DAY_OF_WEEK, 6);
		returnTime.setDateSecond(cal.getTime());
		return returnTime;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(getMonthBegin() + "||" + getMonthEnd());
		System.out.println( getWeekBegin() + "||" + getWeekEnd());
	}
}
