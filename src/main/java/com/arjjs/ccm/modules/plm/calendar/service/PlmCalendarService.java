/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.calendar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.modules.plm.calendar.dao.PlmCalendarDao;
import com.arjjs.ccm.modules.plm.calendar.entity.PlmCalendar;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 工作日历Service
 * @author liuxue
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class PlmCalendarService extends CrudService<PlmCalendarDao, PlmCalendar> {

	public PlmCalendar get(String id) {
		return super.get(id);
	}
	
	public List<PlmCalendar> findList(PlmCalendar plmCalendar) {
		return super.findList(plmCalendar);
	}
	
	public Page<PlmCalendar> findPage(Page<PlmCalendar> page, PlmCalendar plmCalendar) {
		return super.findPage(page, plmCalendar);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCalendar plmCalendar) {
		super.save(plmCalendar);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCalendar plmCalendar) {
		super.delete(plmCalendar);
	}
	@Transactional(readOnly = false)
	public void quickAdd(Date startDate,Date endDate,String title,User user,String type) {
		String CalendarStartTime = DateUtils.formatDate(startDate, "yyyy-MM-dd HH:mm");
		String CalendarEndTime = DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm");
		PlmCalendar plmCalendar =new PlmCalendar();
		String[] begin= String.valueOf(CalendarStartTime).split(" ");
	    String[] end= String.valueOf(CalendarEndTime).split(" ");	 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm");
		
		Date begindate=null;
		Date begintime=null;
		Date enddate=null;
		Date endtime=null;
		try {
			 begindate=sdf.parse(begin[0]);
			 begintime=sdf2.parse(begin[1]);
			 enddate=sdf.parse(end[0]);
			endtime=sdf2.parse(end[1]);	
			plmCalendar.setBeginDate(begindate);
			plmCalendar.setBeginTime(begintime);
			plmCalendar.setEndDate(enddate);
			plmCalendar.setEndTime(endtime);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plmCalendar.setType(type);
		plmCalendar.setSubject(title);
		plmCalendar.setUser(user);
		save(plmCalendar);
	}
}