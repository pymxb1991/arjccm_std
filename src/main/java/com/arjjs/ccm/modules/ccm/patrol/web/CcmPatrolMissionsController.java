/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.utils.JedisUtils;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.alarmnotify.service.BphAlarmNotifyService;
import com.arjjs.ccm.modules.flat.analyst.web.PersonalAnalystController;
import com.arjjs.ccm.modules.flat.deviceonline.web.CcmDeviceOnlineController;
import com.arjjs.ccm.modules.flat.deviceuse.service.CcmDeviceUseService;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.MessageTools;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolMissionsService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 巡逻任务Controller
 * @author lijiupeng
 * @version 2019-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolMissions")
public class CcmPatrolMissionsController extends BaseController {

	@Autowired
	private CcmPatrolMissionsService ccmPatrolMissionsService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SysDictsService sysDictsService;

	@Autowired
	private OaNotifyService oaNotifyService;

	@Autowired
	private BphAlarmNotifyService bphAlarmNotifyService;

	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;

	/**
	 * 巡逻 警卫 备勤 专用状态
	 * 任务进行中状态
	 */
	public static final String PROCESSING_TASK="02";

	/**
	 * 任务类型标识 自定义
	 */
	private final String TYPE="patrol_time_task";

	@ModelAttribute
	public CcmPatrolMissions get(@RequestParam(required=false) String id) {
		CcmPatrolMissions entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPatrolMissionsService.get(id);
		}
		if (entity == null){
			entity = new CcmPatrolMissions();
		}
		return entity;
	}

	@RequestMapping(value = "summaryGraph")
	public String summaryGraph(){

		return "ccm/patrol/summaryGraph";
	}
	@RequestMapping(value = "summaryGraphData")
	@ResponseBody
	public Map<String,Object> summaryGraphData(){

		//查询type
		List<SysDicts> sysDictsList = sysDictsService.findAllListByType("ccm_patrol_missions_status");
		List<String> stringList=new ArrayList<>(sysDictsList.size());
		List<String> valList=new ArrayList<>(sysDictsList.size());
		//lable 值 and val
		sysDictsList.forEach(item->{
			valList.add(item.getValue());
			stringList.add(item.getLabel());
		});


		//查询全部并分类添加个数统计完成情况
		List<CcmPatrolMissions> list = ccmPatrolMissionsService.findList(new CcmPatrolMissions());
		//查询office名称
		list.forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
				idToOfficeName(item.getOffice().split(",")) :
				item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""
		));

		//返回的总data
//		Map<String,Object> mapData=new HashMap<>();
		// 单位
		Map<String,Integer []> mapUnit=new HashMap<>();
//		Map<String,Integer> mapType=new HashMap<>(1);
		// 单位 key 值
		Set<String> strUnit=mapUnit.keySet();
		int size = valList.size();
		list.forEach(item->{
			if(item.getOffice().indexOf(",")!=-1 && item.getOfficeName().indexOf(",")!=-1){
				//String [] splitId=item.getOffice().split(",");
				String [] splitName=item.getOfficeName().split(",");
				for (int i = 0; i < splitName.length; i++) {


					//判断是否有存 当前key
					if (mapUnit.get(splitName[i])==null){
						//type
						Integer [] integers=new Integer[size];
						for (int i1 = 0; i1 < size; i1++) {
							integers[i1]=0;
						}
						//循环状态 判断现在是哪个状态 插入
						for (int i1 = 0; i1 < size; i1++) {
							if(valList.get(i1).equals(item.getStatus())){
								integers[i1]+=1;
							}
						}
						mapUnit.put(splitName[i],integers);
					}else{
						//如果有存过+1
						Integer [] integers=mapUnit.get(splitName[i]);
						for (int i1 = 0; i1 < size; i1++) {
							if(valList.get(i1).equals(item.getStatus())){
								integers[i1]+=1;
							}
						}
						mapUnit.put(splitName[i],integers);

					}
				}
			}else if (item.getOffice().length()>0){
				//只有一个部门的
				String office = item.getOfficeName();
				if (mapUnit.get(office)==null){
					//type
					Integer [] integers=new Integer[size];
					for (int i1 = 0; i1 < size; i1++) {
						integers[i1]=0;
					}
					//循环状态 判断现在是哪个状态 插入
					for (int i1 = 0; i1 < size; i1++) {
						if(valList.get(i1).equals(item.getStatus())){
							integers[i1]+=1;
						}
					}
					mapUnit.put(office,integers);
				}else{
					//如果有存过+1
					Integer [] integers=mapUnit.get(office);
					for (int i1 = 0; i1 < size; i1++) {
						if(valList.get(i1).equals(item.getStatus())){
							integers[i1]+=1;
						}
					}
					mapUnit.put(office,integers);
				}

			}
		});

		Map<String,Object> map=new HashMap<>();
		map.put("typeArr",stringList);
		map.put("mapUnit",mapUnit);
		map.put("key",strUnit);
		return map;
	}


	@RequiresPermissions("patrol:ccmPatrolMissions:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPatrolMissions ccmPatrolMissions, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPatrolMissions> page = ccmPatrolMissionsService.findPage(new Page<CcmPatrolMissions>(request, response), ccmPatrolMissions);
		if(page.getCount()==0){
			model.addAttribute("page", page);
			return "ccm/patrol/ccmPatrolMissionsList";
		}
		page.getList().forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
				idToOfficeName(item.getOffice().split(",")) :
				item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""
		));
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolMissionsList";
	}
	//巡逻任务安排
	@RequestMapping(value = "arrangement")
	public String arrangement(CcmPatrolMissions ccmPatrolMissions, HttpServletRequest request, HttpServletResponse response, Model model) {

		User sessionUser=UserUtils.getUser();
		Page<CcmPatrolMissions> page = null;
		if ("admin".equals(sessionUser.getLoginName())) {
			page = ccmPatrolMissionsService.findPage(new Page<>(request, response), ccmPatrolMissions);
		} else {
			ccmPatrolMissions.setOffice(sessionUser.getOffice().getId());
			page = ccmPatrolMissionsService.findPage(new Page<>(request, response), ccmPatrolMissions);
		}


	/*	//按部门id查询部门名称
		page.getList().forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
				idToOfficeName(item.getOffice().split(",")) :
				item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""


				item.setOfficeName(item.getOffice().indexOf(",") != -1 ? idToOfficeName(item.getOffice().split(",")) : item.getOffice().length() > 0 ? officeService.get(item.getOffice()).getName() : "");
		));*/
		page.getList().forEach(item-> {

			if(item.getOffice() != null ){
				String officeName = "";
				int i = item.getOffice().indexOf(",");
				if(i != -1 ){
					String[] split = item.getOffice().split(",");
					officeName = idToOfficeName(item.getOffice().split(","));
				}else{
					int length = item.getOffice().length();
					if(length> 0 ){
						Office office = officeService.get(item.getOffice());
						if (office != null){
							officeName = office.getName();
						}
					}
				}
				item.setOfficeName(officeName);
			}
		});

		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolMissionsArranggement";
	}
	//查看详情
	@RequestMapping(value = "details")
	public String getDetails(String id,Model model){
		CcmPatrolMissions ccmPatrolMissions = ccmPatrolMissionsService.get(id);

		if(id.length()>0 && ccmPatrolMissions==null){
			return "error/403";
		}else if(StringUtils.isBlank(id)){
			return "error/404";
		}

		ccmPatrolMissions.setOfficeName(
				ccmPatrolMissions.getOffice().indexOf(",")!=-1 ?
						idToOfficeName(ccmPatrolMissions.getOffice().split(",")) :
						ccmPatrolMissions.getOffice().length()>0 ? officeService.get(ccmPatrolMissions.getOffice()).getName() :"");
		model.addAttribute("ccmPatrolMissions",ccmPatrolMissions);
		return "ccm/patrol/ccmPatrolMissionsDetails";

	}

	//查看详情2
	@RequestMapping(value = "details2")
	public String getDetails2(String id,Model model){

		CcmPatrolMissions ccmPatrolMissions = ccmPatrolMissionsService.get(id);

		if(id.length()>0 && ccmPatrolMissions==null){
			return "error/403";
		}else if(StringUtils.isBlank(id)){
			return "error/404";
		}
		ccmPatrolMissions.setOfficeName(
				ccmPatrolMissions.getOffice().indexOf(",")!=-1 ?
				idToOfficeName(ccmPatrolMissions.getOffice().split(",")) :
				ccmPatrolMissions.getOffice().length()>0 ? officeService.get(ccmPatrolMissions.getOffice()).getName() :"");
		model.addAttribute("ccmPatrolMissions",ccmPatrolMissions);
		return "ccm/patrol/ccmPatrolMissionsDetails2";

	}


	/**
	 *如果为null 则没有权限查询
	 */
	private String idToOfficeName(String [] strings){
		String name="";
		for (int i = 0; i < strings.length; i++) {

			Office office = officeService.get(strings[i]);
			if(office==null){
				return null;
			}
			if(i==0){
				name=office.getName();
			}else{
				name+=","+office.getName();
			}
		}
		return name;
	}




	@RequiresPermissions("patrol:ccmPatrolMissions:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolMissions ccmPatrolMissions, Model model) {

		if(EntityTools.isEmpty(ccmPatrolMissions)){
			return "ccm/patrol/ccmPatrolMissionsForm";
		}
		//部门1
		List<String> split =new ArrayList<>();
		if( ccmPatrolMissions.getOffice().indexOf(",") !=-1 &&  ccmPatrolMissions.getOffice().length()>0){
			String [] temp=ccmPatrolMissions.getOffice().split(",");
			//id插入到list
			for (int i = 0; i < temp.length; i++) {
				split.add(temp[i]);
			}
		}else{
			split.add(ccmPatrolMissions.getOffice());
		}

		for (int i = 0; i < split.size(); i++) {
			Office office =officeService.get(split.get(i));
			if(office==null){
				return "error/403";
			}
			ccmPatrolMissions.setOfficeName(i==0 ? office.getName() : ccmPatrolMissions.getOfficeName()+","+office.getName());
		}
		model.addAttribute("ccmPatrolMissions", ccmPatrolMissions);
		return "ccm/patrol/ccmPatrolMissionsForm";
	}

	@RequiresPermissions("patrol:ccmPatrolMissions:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolMissions ccmPatrolMissions, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolMissions)){
			return form(ccmPatrolMissions, model);
		}

		ccmPatrolMissionsService.save(ccmPatrolMissions);

		if(ccmPatrolMissions.getAuditingStatus().equals("2")){

			String officeIds = ccmPatrolMissions.getOffice();
			String taskUrl="/patrol/ccmPatrolMissions/details2?id="+ccmPatrolMissions.getId();
			//发送通知
			if(MessageTools.sendMessageByOfficePersonInCharge(
					oaNotifyService,officeService,officeIds,ccmPatrolMissions.getPatrolContent(),ccmPatrolMissions.getRemarks(),"1",taskUrl)){
				logger.info("巡逻任务发送通知成功：id"+ccmPatrolMissions.getId());
			}else{
				logger.error("巡逻任务发送通知失败：id"+ccmPatrolMissions.getId());
			}

			//通知到手机APP
			Map<String, Object> map = Maps.newHashMap();
			map.put("type",TYPE);
			map.put("id", ccmPatrolMissions.getId());
			map.put("name", ccmPatrolMissions.getPatrolContent());
			if(MessageTools.sendAppMsgByOfficeId(officeService, ccmPatrolMissions.getOffice(), map)){
				logger.info("巡逻任务发送app通知成功：id"+ccmPatrolMissions.getId());
			}else{
				logger.error("巡逻任务发送app通知失败：id"+ccmPatrolMissions.getId());
			}
		}

		addMessage(redirectAttributes, "保存巡逻任务成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolMissions/?repage";
	}
	
	@RequiresPermissions("patrol:ccmPatrolMissions:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolMissions ccmPatrolMissions, RedirectAttributes redirectAttributes) {
		ccmPatrolMissionsService.delete(ccmPatrolMissions);
		addMessage(redirectAttributes, "删除巡逻任务成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolMissions/?repage";
	}



}