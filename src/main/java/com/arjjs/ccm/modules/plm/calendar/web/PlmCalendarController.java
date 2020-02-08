/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.calendar.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.calendar.entity.PlmCalendar;
import com.arjjs.ccm.modules.plm.calendar.service.PlmCalendarService;

import net.sf.json.JSONArray;

/**
 * 工作日历Controller
 * @author liuxue
 * @version 2018-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/calendar/plmCalendar")
public class PlmCalendarController extends BaseController {

	@Autowired
	private PlmCalendarService plmCalendarService;
	
	@ModelAttribute
	public PlmCalendar get(@RequestParam(required=false) String id) {
		PlmCalendar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCalendarService.get(id);
		}
		if (entity == null){
			entity = new PlmCalendar();
		}
		return entity;
	}
	/**
	 * 日历显示
	 * @param plmCalendar
	 * @param request
	 * @param response
	 * @param model
	 * @param showdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequiresPermissions("calendar:plmCalendar:view")
	@RequestMapping(value = {"calendar"})
	public String calendar(PlmCalendar plmCalendar, HttpServletRequest request, HttpServletResponse response, Model model, String showdate) {
		List<PlmCalendar> list = plmCalendarService.findList(plmCalendar); 
		
		//[id,title,start,end，全天日程，跨日日程,循环日程,theme,'地址','']          
	    //[['6147','你好啊',new Date(1338427800000),new Date(1338431400000),0,0,0,0,1,'']];
		List<List<Object>> list2=new ArrayList<List<Object>>();
		
		if(list.size()>0) {
			for (PlmCalendar  plmCalendar2 : list) {
				List<Object>  list3=new ArrayList<Object>();  
				list3.add(0, plmCalendar2.getId());
				
				String subjec=plmCalendar2.getSubject().replaceAll("\'(.*?)\'", "‘$1’").replaceAll("\'", "‘");
				
				list3.add(1, subjec);
				//开始时间
			long begindata=plmCalendar2.getBeginDate().getTime()+plmCalendar2.getBeginTime().getTime();
				
				
				list3.add(2, begindata);
			long enddata=plmCalendar2.getEndDate().getTime()+plmCalendar2.getEndTime().getTime();
			    
				list3.add(3, enddata);
				
				
				
				int hour=0;
				if(plmCalendar2.getBeginTime().getTime()==-28800000||plmCalendar2.getEndTime().getTime()==-28800000) {
					hour=1;
				}
				
				int days=(int) (plmCalendar2.getBeginDate().getDay()-plmCalendar2.getEndDate().getDay()); 
				
				if(days>=1||days<=-1) {
					days=1;
					
				}else {
					days=0;
				}
				
				list3.add(4,hour);
				list3.add(5,days);
				list3.add(6,0);
				int theme=0;
				String Importance=plmCalendar2.getImportance();
				if(StringUtils.isNotBlank(Importance)) {
					if(Importance.equals("3")) {
						theme=1;
					}
				}
				String Priority=plmCalendar2.getPriority();
				if(StringUtils.isNotBlank(Priority)) {
					if(Priority.equals("3")) {
						theme=1;
					}
				}
				list3.add( 7,theme);
				//不用动(是否有删除)
				list3.add(8,0);
				
				if(StringUtils.isNotBlank(plmCalendar2.getSpot())) {
					list3.add(9,plmCalendar2.getSpot().replaceAll("\'(.*?)\'", "‘$1’").replaceAll("\'", "‘"));
				}
				//将list3加入list2中
				list2.add(list3);
			}
		}
		
		JSONArray json = JSONArray.fromObject(list2);  
		   
		model.addAttribute("list", json);	
		if(showdate==null||showdate.equals("")) {
			showdate=String.valueOf(System.currentTimeMillis());
		}
		
		model.addAttribute("date", showdate);	
		return "plm/calendar/plmCalendar";
	}
	 
	/**
	 * 门户日历
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = {"homeCalendar"})
	public String homeCalendar( PlmCalendar plmCalendar,HttpServletRequest request, HttpServletResponse response ,String height ,String width) {
		Date date=new Date();	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		plmCalendar.setBeginBeginDate(date);
		
		List<PlmCalendar> list = plmCalendarService.findList(plmCalendar); 
		
        List<Object> list2=new ArrayList<Object>();
		
		if(list.size()>0) {
			for (PlmCalendar  plmCalendar2 : list) {
				
				Map<String, String> map=new HashMap<>();
				
				String subjec=plmCalendar2.getSubject().replaceAll("\'(.*?)\'", "‘$1’").replaceAll("\'", "‘");
				
				
				//开始时间
				SimpleDateFormat spf1=new SimpleDateFormat("yyyy-MM-dd");
				String  begindata=spf1.format(plmCalendar2.getBeginDate());
				SimpleDateFormat spf2=new SimpleDateFormat("HH:mm");
				String  begintime=spf2.format(plmCalendar2.getBeginTime());
				String  beginend=spf2.format(plmCalendar2.getEndTime());
				
				map.put("value", begintime+" "+subjec);
				map.put("date", begindata);
				if(begintime.equals("00:00")&&begintime.equals(beginend)) {
					
					map.put("value", "全天  "+subjec);
				}
				
				//将list3加入list2中
				list2.add(map);
			}
		}
		
		JSONArray json = JSONArray.fromObject(list2);  
		
	   
	  request.setAttribute("data", json);
	  request.setAttribute("porheigh", height);
	  request.setAttribute("porwidth", width);
	
		return "plm/calendar/plmHomeCalendar";
	}
	/**
	 * 所有日程列表
	 * @param plmCalendar
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("calendar:plmCalendar:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCalendar plmCalendar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCalendar> page = plmCalendarService.findPage(new Page<PlmCalendar>(request, response), plmCalendar); 
		model.addAttribute("page", page);
		return "plm/calendar/plmCalendarList";
	}

	@RequiresPermissions("calendar:plmCalendar:view")
	@RequestMapping(value = "form")
	public String form(PlmCalendar plmCalendar, Model model) {
		model.addAttribute("plmCalendar", plmCalendar);
		
		 String notify=plmCalendar.getNotify();
		 //notify 转成list  用于回显
		List<String> notifylist = new  ArrayList<String>();
		if (StringUtils.isNotBlank(notify)){
			for (String notify2 : StringUtils.split(notify, ",")) {
				notifylist.add(notify2);
			}
		}            
		plmCalendar.setNotifylist(notifylist); 
		return "plm/calendar/plmCalendarForm";
	}
	/**
	 * 弹窗
	 * @param plmCalendar
	 * @param model
	 * @return
	 */
	@RequiresPermissions("calendar:plmCalendar:view")
	@RequestMapping(value = "formTan")
	public String formTan(PlmCalendar plmCalendar, Model model) {
		model.addAttribute("plmCalendar", plmCalendar);
	
		 String notify=plmCalendar.getNotify();
		 //notify 转成list  用于回显
		List<String> notifylist = new  ArrayList<String>();
		if (StringUtils.isNotBlank(notify)){
			for (String notify2 : StringUtils.split(notify, ",")) {
				notifylist.add(notify2);
			}
		}            
		plmCalendar.setNotifylist(notifylist); 
		return "plm/calendar/plmCalendarFormTan";
	}
	
   /**
    * 回  传时间的新增修改
    * @param plmCalendar
    * @param model
    * @return
    */
	@RequiresPermissions("calendar:plmCalendar:view")
	@RequestMapping(value = "formdate")
	public String formdate(PlmCalendar plmCalendar, Model model,@RequestParam Object[] data) {
		
		     String[] begin= String.valueOf(data[2]).split(" ");
		     String[] end= String.valueOf(data[3]).split(" ");	    
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm");
		System.out.println(begin);
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
		     
		plmCalendar.setSubject((String) data[1]);
		
		model.addAttribute("plmCalendar", plmCalendar);		
		return "plm/calendar/plmCalendarFormTan";
	}

	 /**
	  * 保存后返回日历页面
	  * @param plmCalendar
	  * @param model
	  * @param redirectAttributes
	  * @return
	  */
	@RequiresPermissions("calendar:plmCalendar:edit")
	@RequestMapping(value = "save2")
	public String save2(PlmCalendar plmCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCalendar)){
			return form(plmCalendar, model);
		}
		  
		//把提交上来的集合转成字符串
		List<String> notifylist =plmCalendar.getNotifylist();
		String notify="";
		if (notifylist!=null&&notifylist.size()>0){
			notify = ","+StringUtils.join(notifylist, ",")+",";
			plmCalendar.setNotify(notify);
		}
		
		plmCalendar.setUser(plmCalendar.getCurrentUser());
		plmCalendarService.save(plmCalendar);
		addMessage(redirectAttributes, "保存工作日历成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/plmCalendar/calendar?repage";
	}
	
	@RequiresPermissions("calendar:plmCalendar:edit")
	@RequestMapping(value = "save")
	public String save(PlmCalendar plmCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCalendar)){
			return form(plmCalendar, model);
		}
		//把提交上来的集合转成字符串
		List<String> notifylist =plmCalendar.getNotifylist();
		
		String notify="";
		if (notifylist!=null&&notifylist.size()>0){
			notify = ","+StringUtils.join(notifylist, ",")+",";
			plmCalendar.setNotify(notify);
		}
		
		plmCalendar.setUser(plmCalendar.getCurrentUser());
		plmCalendarService.save(plmCalendar);
		addMessage(redirectAttributes, "保存工作日历成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/plmCalendar/?repage";
	}
	
	
	
	@ResponseBody
	@RequiresPermissions("calendar:plmCalendar:edit")
	@RequestMapping(value = "quickadd")
	public Map<String, Object> quickadd(@RequestParam("CalendarTitle") String CalendarTitle ,@RequestParam("CalendarStartTime") String CalendarStartTime,
			             @RequestParam("CalendarEndTime") String CalendarEndTime ) {
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
		
		
		
		plmCalendar.setSubject(CalendarTitle);
		plmCalendar.setUser(plmCalendar.getCurrentUser());
		plmCalendarService.save(plmCalendar);
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("IsSuccess", true);
		
		
		return param;
	}
	
	
	
	
	@RequiresPermissions("calendar:plmCalendar:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCalendar plmCalendar, RedirectAttributes redirectAttributes) {
		plmCalendarService.delete(plmCalendar);
		addMessage(redirectAttributes, "删除工作日历成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/plmCalendar/?repage";
	}

}