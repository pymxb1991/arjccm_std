/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.security.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;
import com.arjjs.ccm.modules.ccm.security.service.CcmPatrolSecurityService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.tool.*;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 警卫管理Controller
 * @author lijiupeng
 * @version 2019-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/security/ccmPatrolSecurity")
public class CcmPatrolSecurityController extends BaseController {

	@Autowired
	private CcmPatrolSecurityService ccmPatrolSecurityService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private SysDictsService sysDictsService;
	/**
	 * 保存时的出警时间 的容错差 毫秒
	 */
	final int SECOND=60000;
    /**
     * 定时任务组的名称
     */
    final String GROUP_NAME = "ccmPatrolSecurity";

	/**
	 * 任务类型标识 自定义
	 */
	private final String TYPE="guard_time_task";

	/**
	 * 审核通过 的状态
	 */
	private final String APPROVED="2";

	@ModelAttribute
	public CcmPatrolSecurity get(@RequestParam(required=false) String id) {
		CcmPatrolSecurity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPatrolSecurityService.get(id);
		}
		if (entity == null){
			entity = new CcmPatrolSecurity();
		}
		return entity;
	}



	@RequestMapping(value = {"summaryGraph",""})
	public String summaryGraph(){

		return "ccm/security/summaryGraph";
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
		List<CcmPatrolSecurity> list = ccmPatrolSecurityService.findList(new CcmPatrolSecurity());
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
		});

		Map<String,Object> map=new HashMap<>();
		map.put("typeArr",stringList);
		map.put("mapUnit",mapUnit);
		map.put("key",strUnit);
		return map;
	}

	
	@RequiresPermissions("security:ccmPatrolSecurity:view")
	@RequestMapping(value = {"list"})
	public String list(CcmPatrolSecurity ccmPatrolSecurity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPatrolSecurity> page = ccmPatrolSecurityService.findPage(new Page<CcmPatrolSecurity>(request, response), ccmPatrolSecurity);
		page.getList().forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
				idToOfficeName(item.getOffice().split(",")) :
				item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""
		));
		model.addAttribute("page", page);
		return "ccm/security/ccmPatrolSecurityList";
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

	@RequiresPermissions("security:ccmPatrolSecurity:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolSecurity ccmPatrolSecurity, Model model) {

		if(EntityTools.isEmpty(ccmPatrolSecurity)){
			return "ccm/security/ccmPatrolSecurityForm";
		}
		//部门1
		List<String> split =new ArrayList<>();
		if( ccmPatrolSecurity.getOffice().indexOf(",") !=-1 &&  ccmPatrolSecurity.getOffice().length()>0){
			String [] temp=ccmPatrolSecurity.getOffice().split(",");
			//id插入到list
			for (int i = 0; i < temp.length; i++) {
				split.add(temp[i]);
			}
		}else{
			split.add(ccmPatrolSecurity.getOffice());
		}

		for (int i = 0; i < split.size(); i++) {
			Office office =officeService.get(split.get(i));
			if(office==null){
				return "error/403";
			}
			ccmPatrolSecurity.setOfficeName(i==0 ? office.getName() : ccmPatrolSecurity.getOfficeName()+","+office.getName());
		}

		model.addAttribute("ccmPatrolSecurity", ccmPatrolSecurity);
		return "ccm/security/ccmPatrolSecurityForm";
	}

	@RequiresPermissions("security:ccmPatrolSecurity:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolSecurity ccmPatrolSecurity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolSecurity)){
			return form(ccmPatrolSecurity, model);

		}
		if(ccmPatrolSecurity.getSecurityTime()==null){
            addMessage(redirectAttributes, "警卫时间不合法");
            return form(ccmPatrolSecurity, model);
        }

		//持久化 获得id
		ccmPatrolSecurityService.save(ccmPatrolSecurity);
        //任务url
		String taskUrl="/security/ccmPatrolSecurity/form?id="+ccmPatrolSecurity.getId();
		if(APPROVED.equals(ccmPatrolSecurity.getAuditingStatus())){

			MessageTools.sendMessageByOfficePersonInCharge(oaNotifyService,officeService,ccmPatrolSecurity.getOffice(),ccmPatrolSecurity.getTitle(),ccmPatrolSecurity.getRemarks(),"1",taskUrl);
			//通知到手机APP
			Map<String, Object> map = Maps.newHashMap();
			map.put("type",TYPE);
			map.put("id", ccmPatrolSecurity.getId());
			map.put("name",ccmPatrolSecurity.getTitle());
			if(MessageTools.sendAppMsgByOfficeId(officeService,ccmPatrolSecurity.getOffice(),map)){
				logger.info("警卫任务发送app通知成功：id"+ccmPatrolSecurity.getId());
			}else{
				logger.error("警卫任务发送app通知失败：id"+ccmPatrolSecurity.getId());
			}
		}

		addMessage(redirectAttributes, "保存警卫成功");
		return "redirect:"+Global.getAdminPath()+"/security/ccmPatrolSecurity/list?repage";
//		return "";
	}



	
	@RequiresPermissions("security:ccmPatrolSecurity:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolSecurity ccmPatrolSecurity, RedirectAttributes redirectAttributes) {
		ccmPatrolSecurityService.delete(ccmPatrolSecurity);
		addMessage(redirectAttributes, "删除警卫成功");
		return "redirect:"+Global.getAdminPath()+"/security/ccmPatrolSecurity/?repage";
	}

}